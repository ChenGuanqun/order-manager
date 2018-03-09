// 这个ajax对象就是一个axios实例，具体用法看axios的文档
import ajax from './index'

export default {
    queryOrder(params) {
        return ajax.post('/api/order/query', {
            orderId: params.orderId,
            customerName: params.customerName,
            productName: params.productName,
            productSeries: params.productSeries,
            status: params.status,
            deliveryStatus: params.deliveryStatus,
            startTime: params.startTime,
            endTime: params.endTime,
            startOrderTime: params.startOrderTime,
            endOrderTime: params.endOrderTime,
            yq: params.yq,
            dz:params.dz,
            zz:params.zz,
            fz:params.fz,
            hbj:params.hbj,
            zc:params.zc,
            gttt:params.gttt,
            jk:params.jk,
            zzzc:params.zzzc,
            dzzc:params.dzzc,
            bz:params.bz,
            bp:params.bp,
            sc:params.sc,
            lc:params.lc,
            pageNum : params.pageNum,
            pageSize : 10
        })
    },

    updateStatus(params) {
        return ajax.post('/api/order/updateStatus', {
            id: params.id,
            status: params.status,
            deliveryStatus: params.deliveryStatus,
            configArray: params.configArray
        })
    },
    deleteOrder(params) {
        return ajax.post('/api/order/delete', {
            id: params.id
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
    updateOrder(params) {
        return ajax.post('/api/order/update', {
            id: params.id,
            customerName: params.customerName,
            productName: params.productName,
            productSeries: params.productSeries,
            number: params.number,
            deliveryDate: params.deliveryDate,
            orderDate: params.orderDate,
            planDate: params.planDate,
            description: params.description
        })
    }
}
