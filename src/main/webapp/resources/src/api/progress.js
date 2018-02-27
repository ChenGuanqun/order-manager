// 这个ajax对象就是一个axios实例，具体用法看axios的文档
import ajax from './index'

export default {
  queryOrder(params) {
    return ajax.post('/api/order/query', {
      params: params
    })
  }
}
