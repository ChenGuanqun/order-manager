// 项目已使用lodash，其它工具函数写在此目录下

export default {
    /**
     * 时间转换
     */
    dateHandle (val, detai) {
        if(val == -1) {
            return "";
        }

        var date = new Date(val);

        var zero = function(str) {
            str = ""+str;
            return str.length <= 1 ? "0"+str : str;
        };

        var maps = {
            'yyyy': function(date){return date.getFullYear()},
            'MM': function(date){return zero(date.getMonth() + 1); },
            'dd': function(date){ return zero(date.getDate()) },
            'HH': function(date){ return zero(date.getHours()) },
            'mm': function(date){ return zero(date.getMinutes())},
            'ss': function(date){ return zero(date.getSeconds())}
        }
        var trunk = /yyyy|MM|dd|HH|mm|ss/g;
        var format = "yyyy-MM-dd HH:mm:ss";
        if(!detai) {
            format = "yyyy-MM-dd";
        }
        return format.replace(trunk, function(capture){
            return maps[capture] ? maps[capture](date): "";
        });
    }

}