const axiosInstance = axios.create();

// 请求拦截器，可以在请求被发送前进行一些操作
axiosInstance.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    console.debug('Request Interceptor:', config);
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 响应拦截器，可以在响应被处理前进行一些操作
axiosInstance.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    console.debug('Response Interceptor:', response);
    return response;
}, function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
});

/**
 * 测试加密解密
 * @param param
 * @returns {*}
 */
const getUserByDecrypt = (param) => axiosInstance.post("../../demo/getUserByDecrypt", param, {
    headers: {
        'Content-Type': 'application/json'
    }
});

/**
 * 添加任务
 * @param param
 * @returns {*}
 */
const jobSubmit = (param) => axiosInstance.post("../../job/jobSubmit", param, {
    headers: {
        'Content-Type': 'application/json'
    }
});

/**
 * 添加任务
 * @param param
 * @returns {*}
 */
const jobListPage = (param) => axiosInstance.get("../../job/listPage", {
    headers: {
        'Content-Type': 'multipart/form-data' // 设置请求头
    },
    params: param
});

/**
 * 获取系统任务分组
 * @returns {*}
 */
const getJobGroup = () => axiosInstance.get("../../job/getJobGroup");


/**
 * 修改任务执行状态
 * @param param
 * @returns {*}
 */
const updateJobStatus = (param) => axiosInstance.post("../../job/updateJobStatus", param, {
    headers: {
        'Content-Type': 'multipart/form-data' // 设置请求头
    }
});

/**
 * 删除任务
 * @param param
 * @returns {*}
 */
const deleteJob = (param) => axiosInstance.post("../../job/deleteJob", param, {
    headers: {
        'Content-Type': 'multipart/form-data' // 设置请求头
    }
});

