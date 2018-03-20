// 这个ajax对象就是一个axios实例，具体用法看axios的文档
import ajax from './index'

export default {
    queryUsers(params) {
        return ajax.post('/api/user/query', {
            userName: params.userName,
            roleName: params.roleName
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
            password:params.password,
            description: params.description
        })
    },

  updatePwd(params) {
    return ajax.post('/api/user/updatePwd', {
      newPwd:params.newPwd,
      newAgain: params.newAgain
    })
  },
    getCurrentUserName() {
        return ajax.post('/api/user/queryCurrentUserName')
    },
    getRole () {
    return ajax.post('/api/role/getRole')
    },
    queryRoles () {
    return ajax.post('/api/role/query', {
      pageNum:1,
      pageSize:30
    })
  }
}
