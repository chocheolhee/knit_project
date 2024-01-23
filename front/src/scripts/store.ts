import {createStore} from 'vuex'

const store = createStore({
    state() {
        return {
            account: {
                id: 0
            }
        }
    },
    mutations: {
        setAccount(state: any, payLoad: any) {
            state.account.id = payLoad
        },
    }
})

export default store