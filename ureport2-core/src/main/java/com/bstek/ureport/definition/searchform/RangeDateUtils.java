package com.bstek.ureport.definition.searchform;

/**
 * <pre>
 *    @author  : wangchun
 *    @time    : 2020/7/2 10:50
 *    @desc    : 日期范围控制帮助类
 *    @version : v1.0
 * </pre>
 */
public abstract class RangeDateUtils {
    /**
     * 开始范围结束判断
     */
    public static final String START_DATE = "StartDate";
    /**
     * 结束日期范围判断
     */
    public static final String END_DATE = "EndDate";
    /**
     * 替换开始词语
     */
    public static final String REPLACE_START_WORD = "style=";
    /**
     * 替换结束词语
     */
    public static final String REPALCE_END_WORD = "style='padding-left:2px;padding-right:2px'";

    private static final String START_DATE_PLACEHOLDER = "开始时间";

    private static final String END_DATE_PLACEHOLDER = "结束时间";
    /**
     * 单选结尾标志
     */
    public static final String SINGLE_SELECT = "Single";
    /**
     * 多选结尾标志
     */
    public static final String MULTIPLE_SELECT = "CheckBox";
    /**
     * 判断是否需要分组
     */
    public static final String CHARGE_ITEM = "收费科目";
    /**
     * 是否抄表
     */
    public static final String IS_METER_READING = "Y";

    /**
     * 构造样式
     * @param sb
     */
    public static void buildColStyle(StringBuffer sb,String type,String label){
        if(type.endsWith(START_DATE)){
            sb.append("<span class='col-md-4' style='text-align:right;padding-right:10px;font-size:13px;line-height:34px;width: 51%;'>").append(label).append("</span>")
              .append("<div class='col-md-8' style='width: 49%;margin: 0;padding: 0;'>");
        }else if(type.endsWith(END_DATE)){
            sb.append("<span class='col-md-4' style='font-size:13px;line-height:34px;width: 12px;margin: 0;padding: 0;'>").append(label).append("</span>")
              .append("<div class='col-md-8' style='display: inline-block;width: calc(100% - 26px);padding: 0;margin: 0;'>");
        }else{
            sb.append("<span class='col-md-4' style='text-align:right;padding-right:10px;font-size:13px;line-height:34px'>").append(label).append("</span>")
              .append("<div class='col-md-8' style='padding-left:1px;'>");
        }
    }

    /**
     * 创建 placeholder展示
     * @param type
     * @param label
     * @return
     */
    public static String buildDatePlaceHolder(String type, String label) {
        if(type.endsWith(START_DATE)){
            return START_DATE_PLACEHOLDER;
        }else if(type.endsWith(END_DATE)){
           return END_DATE_PLACEHOLDER;
        }
        return "请选择"+label;
    }
}
