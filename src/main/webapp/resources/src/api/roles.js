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

      orderId:params.orderId,
      customerName:params.customerName,
      productName:params.productName,
      productSeries:params.productSeries,
      number:params.number,
      deliveryDate:params.deliveryDate,
      orderDate:params.orderDate,
      planDate:params.planDate,
      config:params.config,
      status:params.status,
      deliveryStatus:params.deliveryStatus,
      operate:params.operate,
      description:params.description
    })
  },
  updateRole(params) {
    return ajax.post('/api/role/update', {
      id:params.id,
      roleName:params.roleName,

      orderId:params.orderId,
      customerName:params.customerName,
      productName:params.productName,
      productSeries:params.productSeries,
      number:params.number,
      deliveryDate:params.deliveryDate,
      orderDate:params.orderDate,
      planDate:params.planDate,
      config:params.config,
      status:params.status,
      deliveryStatus:params.deliveryStatus,
      operate:params.operate,
      description:params.description
    })
  },
  deleteRole(params) {
    return ajax.post('/api/role/delete',{
      id:params.id
    })
  }
}
