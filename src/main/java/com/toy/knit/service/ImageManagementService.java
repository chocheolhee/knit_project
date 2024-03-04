package com.toy.knit.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.toy.knit.entity.Image;
import com.toy.knit.repository.image.ImageRepository;
import com.toy.knit.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class ImageManagementService {

    private final AmazonS3Client amazonS3Client;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    // S3 버킷 이름
    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    //섬네일 파일 업로드
    public String imageUpload(MultipartFile multipartFile, String dirName, Long postId) throws IOException {

        File uploadFile = convert(multipartFile)  // 파일 변환할 수 없으면 에러
                .orElseThrow(() -> new IllegalArgumentException(
                        "error: MultipartFile -> File convert fail"));

        return imageUpload(uploadFile, dirName, postId);
    }

    // 로컬에 파일 업로드 하기
    private Optional<File> convert(MultipartFile file) throws IOException {

        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());

        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)

            try (FileOutputStream fos = new FileOutputStream(
                    convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }

            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

    // S3로 섬네일 파일 업로드하기
    private String imageUpload(File uploadFile, String dirName, Long postId) {

        String extension = getExtension(uploadFile);

        String fileName = dirName + "/" + UUID.randomUUID() + "." + extension; // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        removeNewFile(uploadFile);

        String key = fileName.replace(dirName + "/", ""); // 키 값 저장.

        //DB에 정보 저장.
        Image image = Image.builder()
                .originalName(uploadFile.getName()) // 파일 원본 이름
                .imageKey(key).build();

        registerImage(image, postId);

        return uploadImageUrl;
    }

    private String getExtension(File uploadFile) {
        String uploadName = uploadFile.getName();
        String extension = uploadName.substring(uploadName.lastIndexOf(".") + 1);
        extension = extension.toLowerCase();

        //이미지 파일 확장자가 아닌 경우 exception 발생.
        if (!extension.equals("bmp") && !extension.equals("rle") && !extension.equals("dib")
                && !extension.equals("jpeg") && !extension.equals("jpg")
                && !extension.equals("png") && !extension.equals("gif")
                && !extension.equals("jfif") && !extension.equals("tif")
                && !extension.equals("tiff") && !extension.equals("raw")) {
            throw new IllegalStateException("이미지 확장자가 아닙니다.");
        }
        return extension;
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {

        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {

        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }

        log.info("File delete fail");
    }

    private void registerImage(Image postImage, Long postId) {

        postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("해당 id에 속하는 게시글이 존재하지 않습니다."));

        imageRepository.save(postImage);
    }

}
