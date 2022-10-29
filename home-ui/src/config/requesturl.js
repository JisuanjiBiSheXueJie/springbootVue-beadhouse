import request from "@/utils/request";
import {ElMessage} from "element-plus";
import axios from "axios";
//获取详细的审核信息
const Bxapply = "/info/getBxapply";
const DetailedInfo = "/info/findDetailedById/";
const updataBx = "/info/updateBx";
const insertBx = "/info/ReportToMaintenance";
const SSListInfo = "/info/getSSListInfo";
const Ilogin = "/user/login";
const IgetNews = "/info/getNews";
const IgetEAndWByhouseNum = "/info/getEAndWByhouseNum/";
const Ipay = "/user/pay/";
const IApplyFor = "/info/AskForLeave";
const Ihealthpunch = "/info/Healthpunch";
const IupdateHealthpunch = "/info/updateHealthpunch";
const Idxyzm = "/util/getVerificationCode";
const IpdYzm = "/util/JudgeVerificationCode";
const Iupdate = "/user/update";
const IadminLogin = "/user/adminLogin";
const IinitView = "/info/initView";
const IgetNewsById = "/info/getNewsById/"
const IgetPersonListByUser = "/person/getPersonListByUser";
const IgetAddRemaining = "/person/getAddRemaining";
const IgetPersonnelDrug = "/person/getPersonnelDrug/";
const IgetPerFoodsById = "/person/getPerFoodsById/";
const IgetInitViewDrug = "/info/getInitViewDrug";
const IgetInitViewFoods = "/info/getInitViewFoods";
const IgetAllUser = "/info/getAllUser";
const IgetListByName = "/info/getListByName/";
const IadminAddUser = "/user/adminAddUser";
const IsetUserNotUse = "/user/setUserNotUse/"
const IselectPage = "/user/selectPage"
const IfindUserById = "/user/findUserById/";
const IgetPersonnelUser = "/person/getPersonnelUser/";
const IgetBxList = "/info/getBxList";
const IsetStatusIsBackTo = "/info/setBxStatus";
const IgeterrorReasonById = "/info/getErrorReasonById/";
const IgetAllLeaveForUser = "/info/getAllLeaveForUser";
const IsetLeaveStatusById = "/info/setLeaveStatusById";
const IgetLeaveErrorReasonById = "/info/getLeaveErrorReasonById/";
const IgetPersonPage = "/person/getPersonPage";
const IgetPersonMessById = "/person/getPersonMessById/";
const IdelPersonById = "/person/delPersonById/";
const IgetNewsType = "/info/getNewsType";
const IinsertNews = "/info/insertNews"
const IgetNewsIsAdmin = "/info/getNewsIsAdmin"
const IchangeNews = "/info/changeNews";
const IgetMonitor = "/monitor/server";
const IgetDatabaseMess = "/monitor/getDatabaseMess";
const IgetCache = "/monitor/cache";
//获取图片地址
const IgetImageFileUrl = "/api/files/";

const IsaveLeaveAndMessage = "/guest/saveLeaveAndMessage";
const IsaveVisitor = "/guest/saveVisitor";

const IupdateVisitor = "/guest/updateVisitor";

const IgetIndexIntroduce = "/info/getIndexIntroduce";

const IgetIndexIntroduceById= "/info/getIndexIntroduceById/";

const IinsertIndexIntroduce = "/info/insertIndexIntroduce";

const IgetIndexIntroduceByType = "/api/info/getIndexIntroduceByType/";
const IgetVisitor = "/guest/getVisitor";
const IgenerateCode = "/util/generateCode/";
const IgetHealthpunch= "/info/getHealthpunch";
const IgetHealthpunchById= "/info/getHealthpunchById";

const IgetInitViewDrugPage= "/info/getInitViewDrugPage";
const IaddDrug= "/info/addDrug";
const IfindMedlicineById= "/info/findMedlicineById/";

const IupdateMedlicine= "/info/updateMedlicine";

const IgetInitViewFoodPage= "/info/getInitViewFoodPage";
const IaddFood= "/info/addFood";
const IfindFoodById= "/info/findFoodById/";

const IupdateFood= "/info/updateFood";
const IaddPerson= "/person/addPerson";


const IgetPersonById= "/person/getPersonById/";

const IchangeIndexIntroduce= "/info/changeIndexIntroduce";
const IgetMessageList= "/info/getMessageList";

const IupdateMessage= "/info/updateMessage";

const IgetLeaveList= "/info/getLeaveList";
const IfileWord= "/files/word/";

const IgetDrugTypeList= "/info/getDrugTypeList/";

const IupdatePersonnelDrug= "/info/updatePersonnelDrug";

const IgetFoodOptions= "/info/getFoodTypeList";
const IupdatePersonnelFood= "/info/updatePersonnelFood";
export default class httpRequest {
    //获取报修信息
    getBxapply() {
        return request.get(Bxapply);
    }

    //根据报修id获取详细信息
    findDetailedById(id) {
        return request.get(DetailedInfo + id);
    }

    //更新报修表
    updateBx(data) {
        return request.post(updataBx, data);
    }

    //报修提交信息
    bxInsert(data) {
        return request.post(insertBx, data)
    }

    //获取宿舍楼牌号信息
    getSSListInfo() {
        return request.get(SSListInfo);
    }

    //登录
    login(data) {
        return request.post(Ilogin, data);
    }

    //获取新闻列表
    getNews() {
        return request.get(IgetNews);
    }

    //获取水电费信息
    getEAndWByhouseNum(data) {
        return request.get(IgetEAndWByhouseNum + data);
    }

    //支付
    pay(data) {
        return request.post(Ipay + data);
    }

    //请假
    applyFor(data) {
        return request.post(IApplyFor, data);
    }

    healthpunch(data) {
        return request.post(Ihealthpunch, data);
    }

    //获取验证码
    getYZM() {
        return request.post(Idxyzm);
    }

    //判断验证码
    pdYzm(data) {
        return request.post(IpdYzm, data);
    }

    //更新用户
    update(data) {
        return request.post(Iupdate, data);
    }

    //管理员登录
    adminLogin(data) {
        return request.post(IadminLogin, data);
    }

    //初始化页面
    initView() {
        return request.post(IinitView);
    }

    //根据id获取新闻信息
    getNewsById(data) {
        return request.get(IgetNewsById + data);
    }

    //获取人员列表根据用户
    getPersonListByUser() {
        return request.get(IgetPersonListByUser);
    }

    //获取护工剩余可照顾人数
    getAddRemaining() {
        return request.get(IgetAddRemaining);
    }

    //获取用药信息
    getPersonnelDrug(data) {
        return request.get(IgetPersonnelDrug + data);
    }

    //获取食品信息
    getPerFoodsById(data) {
        return request.get(IgetPerFoodsById + data);
    }

    getInitViewFoods() {
        return request.get(IgetInitViewFoods);
    }

    getInitViewDrug() {
        return request.get(IgetInitViewDrug);
    }

    //获取全部用户信息
    getAllUser() {
        return request.get(IgetAllUser);
    }

    //根据名字查找
    getListByName(name) {
        return request.get(IgetListByName + name);
    }

    //添加用户
    adminAddUser(data) {
        return request.post(IadminAddUser, data);
    }

    //将用户设置为禁用
    setUserNotUse(data) {
        return request.post(IsetUserNotUse + data);
    }

    //根据分页信息
    selectPage(data) {
        return request.post(IselectPage, data);
    }

    //根据用户id查找
    findUserById(data) {
        return request.get(IfindUserById + data);
    }

    //根据id获取绑定的护工
    getPersonnelUser(data) {
        return request.get(IgetPersonnelUser + data);
    }

    //获取报修但
    getBxList() {
        return request.get(IgetBxList);
    }

    //将报修申请打回
    setStatusIsBackTo(data) {
        return request.post(IsetStatusIsBackTo, data);
    }

    //根据id获取信息
    getErrorReasonById(data) {
        return request.get(IgeterrorReasonById + data);
    }

    //获取全部外出信息
    getAllLeaveForUser() {
        return request.get(IgetAllLeaveForUser);
    }

    //根据id修改状态信息
    setLeaveStatusById(data) {
        return request.post(IsetLeaveStatusById, data);
    }

    getLeaveErrorReasonById(data) {
        return request.get(IgetLeaveErrorReasonById + data);
    }

    //获取全部用户信息
    getPersonPage(data) {
        return request.post(IgetPersonPage, data);
    }

    getPersonMessById(data) {
        return request.get(IgetPersonMessById + data);
    }

    delPersonById(data) {
        return request.post(IdelPersonById + data);
    }

    getNewsType() {
        return request.get(IgetNewsType);
    }

    insertNews(data) {
        return request.post(IinsertNews, data);
    }

    getNewsIsAdmin(data) {
        return request.post(IgetNewsIsAdmin, data);
    }

    changeNews(data) {
        return request.post(IchangeNews, data);
    }

    getMonitor() {
        return request.get(IgetMonitor);
    }

    getDatabaseMess() {
        return request.get(IgetDatabaseMess);
    }
    getCache(){
        return request.get(IgetCache);
    }
    //获取食品信息
    getFoodList(){
        return request.get("/guest/getFoodList");
    }
    getImageFileUrl(){
        return IgetImageFileUrl;
    }
    saveLeaveAndMessage(data){
        return request.post(IsaveLeaveAndMessage,data);
    }
    saveVisitor(data){
        return request.post(IsaveVisitor,data);
    }
    getIndexIntroduce(data){
        return request.post(IgetIndexIntroduce,data);
    }
    getIndexIntroduceById(data){
        return request.get(IgetIndexIntroduceById+data);
    }
    insertIndexIntroduce(data){
        return request.post(IinsertIndexIntroduce,data);
    }
    getIndexIntroduceByType(data){
        return axios.get(IgetIndexIntroduceByType+data);
    }
    getVisitor(data){
        return request.post(IgetVisitor,data);
    }
    generateCode(data){
        return request.post(IgenerateCode+data);
    }
    getHealthpunch(data){
        return request.post(IgetHealthpunch,data);
    }
    getHealthpunchById(data){
        return request.post(IgetHealthpunchById,data);
    }
    getInitViewDrugPage(data){
        return request.post(IgetInitViewDrugPage,data);
    }
    addDrug(data){
        return request.post(IaddDrug,data);
    }
    findMedlicineById(data){
        return request.post(IfindMedlicineById+data);
    }
    updateMedlicine(data){
        return request.post(IupdateMedlicine,data);
    }
    getInitViewFoodPage(data){
        return request.post(IgetInitViewFoodPage,data);
    }
    addFood(data){
        return request.post(IaddFood,data);
    }
    findFoodById(data){
        return request.post(IfindFoodById+data);
    }
    updateFood(data){
        return request.post(IupdateFood,data);
    }
    updateHealthpunch(data){
        return request.post(IupdateHealthpunch,data);
    }
    addPerson(data){
        return request.post(IaddPerson,data);
    }
    getPersonById(data){
        return request.post(IgetPersonById+data);
    }
    updateVisitor(data){
        return request.post(IupdateVisitor,data);
    }
    changeIndexIntroduce(data){
        return request.post(IchangeIndexIntroduce,data);
    }
    getMessageList(data){
        return request.post(IgetMessageList,data);
    }
    updateMessage(data){
        return request.post(IupdateMessage,data);
    }
    getLeaveList(data){
        return request.post(IgetLeaveList,data);
    }
    fileWord(data){
        return request.post(IfileWord+data);
    }
    getDrugTypeList(data){
        return request.get(IgetDrugTypeList,data);
    }
    updatePersonnelDrug(data){
        return request.post(IupdatePersonnelDrug,data);
    }
    getFoodOptions(){
        return request.get(IgetFoodOptions);
    }
    updatePersonnelFood(data){
        return request.post(IupdatePersonnelFood,data);
    }
}

//判断状态码是否成功
export function isSuccess(res) {
    return res.status === 200 ? true : false;
}

//成功消息提示
export function successMessage(res) {
    ElMessage.success(res);
}

//失败消息提示
export function errorMessage(res) {
    ElMessage.error(res);
}
