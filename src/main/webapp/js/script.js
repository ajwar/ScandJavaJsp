//Нахождения количества дней в текущем месяце
Date.prototype.dayInMonth=function(){
    return 33- new Date(this.getFullYear(),this.getMonth(),33).getDate();
}
//Функция возвращает первый месяц текущего квартала
Date.prototype.monthIsQtr=function(){
    var n=new Date(this.getFullYear(),this.getMonth(),this.getDate()).getMonth()+1;
    var m=(n-1-(n-1)%3)/3+1;
    var nm;
    switch (m) {
        case 1:
            nm = 0;
            break;
        case 2:
            nm = 3;
            break;
        case 3:
            nm = 6;
            break;
        case 4:
            nm = 9;
            break;
    }
    return nm;
}
//Перевод даты в нормальный формат,без подключения сторонних библиотек
var months=["янв","фев","мар","апр","мая","июн","июл","авг","сен","окт","ноя","дек"];
function funcDate(year,month,day){
    var tempDate=new Date(year,month,day);
    return (months[month]+" "+tempDate.getDate()+","+tempDate.getFullYear());
}
//Собственно сама функция,которая заполняет форму в jsp при событии onchange
function timePeriod(){
    var str=document.getElementById("period").value;
    var strStartDate=document.getElementById("strStartDateId");
    var strEndDate=document.getElementById("strEndDateId");
    switch (str) {
        case "Last Qtr"://Предыдущий квартал
            var date=new Date();
            date.setDate(0);date.setDate(0);date.setDate(0);//Откатываю дату на 3 месяца назад
            strStartDate.value=funcDate(date.getFullYear(),date.monthIsQtr(),1);
            date.setMonth(date.monthIsQtr()+2);
            strEndDate.value=funcDate(date.getFullYear(),date.getMonth(),date.dayInMonth());
            break;
        case "Last Month"://Предыдущий месяц
            var date=new Date();
            //Делает дату последним числом предыдущего месяца
            date.setDate(0);
            strStartDate.value=funcDate(date.getFullYear(),date.getMonth(),1);
            strEndDate.value=funcDate(date.getFullYear(),date.getMonth(),date.getDate());
            break;
        case "Last Calendar Year": //Предыдущий год
            var date=new Date();
            strStartDate.value=funcDate(date.getFullYear()-1,0,1);
            strEndDate.value=funcDate(date.getFullYear()-1,11,31);
            break;
        case "Current Year to Date"://Этот год
            var date=new Date();
            strStartDate.value=funcDate(date.getFullYear(),0,1);
            strEndDate.value=funcDate(date.getFullYear(),11,31);
            break;
        case "Current Qtr to Date"://Диапазон квартала этого месяца
            var date=new Date();
            strStartDate.value=funcDate(date.getFullYear(),date.monthIsQtr(),1);
            date.setMonth(date.monthIsQtr()+2);
            strEndDate.value=funcDate(date.getFullYear(),date.getMonth(),date.dayInMonth());
            break;
        case "Current Month to Date"://Этот месяц в этом году
            var date=new Date();
            strStartDate.value=funcDate(date.getFullYear(),date.getMonth(),1);
            strEndDate.value=funcDate(date.getFullYear(),date.getMonth(),date.dayInMonth());
            break;
        default:
            strStartDate.value="";
            strEndDate.value="";
            return true;
    }
}
//Функция проверки валидности формы перед отправкой на сервер
function checkForm(form){
    var start=document.getElementById('strStartDateId');
    var end=document.getElementById('strEndDateId');
    var format="MMM d,yyyy".length;
    if (months.join().indexOf(start.value.substring(0,3))==-1 || months.join().indexOf(end.value.substring(0,3))==-1){
        document.getElementById('errStartDate').innerHTML='Месяц должен быть на русском языке в формате МММ.';
        return false;
    }
    if (start.value.trim().length < format || end.value.trim().length <=format){
        document.getElementById('errEndDate').innerHTML='Неправильный формат данных,нужный формат MMM d,yyyy';
        return false;
    }

    return "SUCCESS";
}
