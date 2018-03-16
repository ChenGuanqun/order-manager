import ajax from './index'

export default {
  queryRoles () {
    return ajax.post('/api/role/query', {
      pageNum:1,
      pageSize:30
    })
  },
  createRole(params) {
    return ajax.post('/api/role/create', {
      roleName:params.roleName,
      description:params.description
    })
  }
}
