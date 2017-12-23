var dateUtil = {

    //EasyUI用DataGrid用日期格式化
    TimeFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        /*json格式时间转js时间格式*/
        value = value.substr(1, value.length - 2);
        var obj = eval('(' + "{Date: new " + value + "}" + ')');
        var dateValue = obj["Date"];
        if (dateValue.getFullYear() < 1900) {
            return "";
        }
        var val = dateValue.format("yyyy-mm-dd HH:MM");
        return val.substr(11, 5);
    },
    DateTimeFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        /*json格式时间转js时间格式*/
        value = value.substr(1, value.length - 2);
        var obj = eval('(' + "{Date: new " + value + "}" + ')');
        var dateValue = obj["Date"];
        if (dateValue.getFullYear() < 1900) {
            return "";
        }

        return dateValue.format("yyyy-mm-dd HH:MM");
    },

    //EasyUI用DataGrid用日期格式化
    DateFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        /*json格式时间转js时间格式*/
        value = value.substr(1, value.length - 2);
        var obj = eval('(' + "{Date: new " + value + "}" + ')');
        var dateValue = obj["Date"];
        if (dateValue.getFullYear() < 1900) {
            return "";
        }

        return dateValue.format("yyyy-mm-dd");
    },
    TitleFormatter : function (value, rec, index) {
    	value=value+"";
        if (value.length > 10) value = value.substr(0, 8) + "...";
        return value;
    },
    LongTitleFormatter: function (value, rec, index) {
    	value=value+"";
        if (value.length > 15) value = value.substr(0, 12) + "...";
        return value;
    },
	toTimeStamp:function jsonTimeStamp(milliseconds,rec, index) {
						        if (milliseconds != "" && milliseconds != null
						                && milliseconds != "null") {
						            var datetime = new Date();
						            datetime.setTime(milliseconds);
						            var year = datetime.getFullYear();
						            var month = datetime.getMonth() + 1 < 10 ? "0"
						                    + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
						            var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
						                    : datetime.getDate();
						            var hour = datetime.getHours() < 10 ? "0" + datetime.getHours()
						                    : datetime.getHours();
						            var minute = datetime.getMinutes() < 10 ? "0"
						                    + datetime.getMinutes() : datetime.getMinutes();
						            var second = datetime.getSeconds() < 10 ? "0"
						                    + datetime.getSeconds() : datetime.getSeconds();
						            return year + "-" + month + "-" + date + " " + hour + ":" + minute
						                    + ":" + second;
						        } else {
						            return "";
						        }
						    },
    toYearMonthDay:function jsonYearMonthDay(milliseconds,rec, index) {
        var datetime = new Date();
        datetime.setTime(milliseconds);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1 < 10 ? "0"
                + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
                : datetime.getDate();
        return year + "-" + month + "-" + date;
    }			    
						    
};