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
            roleName:params.roleName,
            description: params.description
        })
    },

    deleteUser(params) {
        return ajax.post('/api/user/delete', {
            id: params.id
        })
    },

    updateUser(params) {
        return ajax.post('/api/user/update', {
            id: params.id,
            status: params.status,
            roleName:params.roleName,
            description: params.description
        })
    },

    getCurrentUserName() {
        return ajax.post('/api/user/queryCurrentUserName')
    },
    queryRoles () {
    return ajax.post('/api/role/query', {
      pageNum:1,
      pageSize:30
    })
  }
}
