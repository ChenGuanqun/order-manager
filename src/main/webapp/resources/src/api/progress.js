// 这个ajax对象就是一个axios实例，具体用法看axios的文档
import ajax from './index'

export default {
    queryOrder(params) {
        return ajax.post('/api/order/query', {
            customerName: params.customerName,
            productName: params.productName,
            status: params.status,
            startTime: params.startTime,
            endTime: params.endTime
        })
    },

    updateOrder(params) {
        return ajax.post('/api/order/update', {
            id: params.id,
            status: params.status,
            configArray: params.configArray,
            description: params.description
        })
    },
    createOrder(params) {
        return ajax.post('/api/order/create', {
            customerName: params.customerName,
            productName: params.productName,
            productSeries: params.productSeries,
            number: params.number,
            deliveryDate: params.deliveryDate,
            orderDate: params.orderDate,
            planDate: params.planDate,
            description: params.description
        })
    },
}
