package com.third.annotation;//package com.third.annotation;
//
//import com.alibaba.fastjson.JSONObject;
//import com.udinovo.http.response.RestResult;
//import com.udinovo.weixin.service.WeixinService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.Map;
//import java.util.logging.Logger;
//
///**
// * @descript 检查项拦截器
// */
//@Component
//@Slf4j
//public class AuditInterceptor implements HandlerInterceptor {
//
//
//    @Autowired
//    private WeixinService weixinService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(log.isDebugEnabled()){
//            debugRequest(request, response, (HandlerMethod) handler);
//        }
//
//
//        //如果不是映射到方法直接通过
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//
//        //如果检查到有需要审核的注解
//        Audit audit = method.getAnnotation(Audit.class);
//        if (audit == null) {
//            return true;
//        }else{//有注解
//
//            AuditItem[] auditItems = audit.items();//拿到审核项
//            if(auditItems!=null && auditItems.length>0){
//                boolean auditStatus = true;
//                String tip = "";
//                String param = "";
//                for (int i = 0;i<auditItems.length;i++){
//                    String value = auditItems[i].value();
//                    param = auditItems[i].value();
//                    String content = request.getParameter(value);
//                    if (auditItems[i].mediaType().equals(com.udinovo.weixin.audit.AuditType.IMAGE)){//图片审核
//                        String[] contents = content.split(",");
//                        if(contents!= null && contents.length>0){
//                            for(int j = 0;j<contents.length;j++){
//                                RestResult<JSONObject> jsonObjectRestResult = weixinService.media_check_async(contents[j],2);
//                                if(jsonObjectRestResult.getErrno()==0 && jsonObjectRestResult.getData().get("isrisky")!=null && jsonObjectRestResult.getData().get("isrisky").equals(1)){
//                                    auditStatus = false;
//                                    tip = auditItems[i].tip();
//                                    break;
//                                }
//                                if(jsonObjectRestResult.getErrno()!=0 && jsonObjectRestResult.getData()!=null){
//                                    auditStatus = false;
//                                    tip = jsonObjectRestResult.getData().toJSONString();
//                                    break;
//                                }
//                            }
//                        }
//                    }else if (auditItems[i].mediaType().equals(com.udinovo.weixin.audit.AuditType.MSG)){
//                        RestResult<JSONObject> jsonObjectRestResult = weixinService.msg_sec_check(content);
//                        if(jsonObjectRestResult.getErrno()==0 && jsonObjectRestResult.getData().get("errcode").equals(87014)){
//                            auditStatus = false;
//                            tip = auditItems[i].tip();
//                            break;
//                        }
//                        if(jsonObjectRestResult.getErrno()!=0 && jsonObjectRestResult.getData()!=null){
//                            auditStatus = false;
//                            tip = jsonObjectRestResult.getData().toJSONString();
//                            break;
//                        }
//                    }
//                }
//                if(!auditStatus){
//                    response.setStatus(200);
//                    response.setCharacterEncoding("UTF-8");
//                    response.setContentType("application/json; charset=utf-8");
//                    JSONObject res = new JSONObject();
//                    res.put("errmsg",tip);
//                    res.put("param",param);
//                    res.put("errno",666);
//                    log.error("");
//                    PrintWriter out = null ;
//                    out = response.getWriter();
//                    out.write(res.toString());
//                    out.flush();
//                    out.close();
//                    return false;
//                }
//            }
//
//        }
//
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//
//    private void debugRequest(HttpServletRequest request, HttpServletResponse response, Object handler){
//        // 所有请求第一个进入的方法
//        String reqURL = request.getRequestURL().toString();
//        String ip = request.getRemoteHost();
//
//        if (handler instanceof HandlerMethod) {
//            StringBuilder sb = new StringBuilder(1000);
//            HandlerMethod h = (HandlerMethod) handler;
//            // Controller 的包名
//            sb.append("Controller    : ").append(h.getBean().getClass().getName()).append("\n");
//            // 方法名称
//            sb.append("Method        : ").append(h.getMethod().getName()).append("\n");
//            // 请求方式 post\put\get 等等
//            //sb.append("RequestMethod : ").append(request.getMethod()).append("\n");
//            // 所有的请求参数
//            sb.append("Params        : ").append(getParamString(request.getParameterMap())).append("\n");
//            // 部分请求链接
//            //sb.append("URI           : ").append(request.getRequestURI()).append("\n");
//            // 完整的请求链接
//            //sb.append("AllURI        : ").append(reqURL).append("\n");
//            // 请求方的 ip地址
//            //sb.append("request IP    : ").append(ip).append("\n");
//            log.info(sb.toString());
//
//        }
//    }
//
//    private String getParamString(Map<String, String[]> map) {
//        StringBuilder sb = new StringBuilder();
//        for (Map.Entry<String, String[]> e : map.entrySet()) {
//            sb.append(e.getKey()).append("=");
//            String[] value = e.getValue();
//            if (value != null && value.length == 1) {
//                sb.append(value[0]).append("\t");
//            }
//            else {
//                sb.append(Arrays.toString(value)).append("\t");
//            }
//        }
//        return sb.toString();
//    }
//}
