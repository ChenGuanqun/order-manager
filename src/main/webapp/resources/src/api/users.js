// 这个ajax对象就是一个axios实例，具体用法看axios的文档
import ajax from './index'

export default {
    queryUsers(params) {
        return ajax.post('/api/user/query', {
            userName: params.userName
        })
    },

    createUser(params) {
        return ajax.post('/api/user/create', {
            userName: params.userName,
            description: params.description
        })
    }
}
