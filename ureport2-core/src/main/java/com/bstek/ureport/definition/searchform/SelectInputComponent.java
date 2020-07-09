/*******************************************************************************
 * Copyright 2017 Bstek
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.bstek.ureport.definition.searchform;

import com.bstek.ureport.Utils;
import com.bstek.ureport.build.Dataset;
import com.bstek.ureport.exception.DatasetUndefinitionException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年10月23日
 */
public class SelectInputComponent extends InputComponent {
	private boolean useDataset;
	private String dataset;
	private String labelField;
	private String valueField;
	private List<Option> options;
	@Override
	String inputHtml(RenderContext context) {
		String name=getBindParameter();
		Object pvalue=context.getParameter(name)==null ? "" : context.getParameter(name);
		StringBuilder sb=new StringBuilder();
		sb.append("<select style='padding:3px' title = '请选择"+this.getLabel()+"' data-live-search='true' data-size='7' id='"+context.buildComponentId(this)+"' name='"+name+"' class='form-control selectpicker' ");
		if(name.endsWith(RangeDateUtils.MULTIPLE_SELECT)){
			sb.append("data-selected-text-format='count > 3' data-actions-box='true'  multiple");
		}
		sb.append(" > ");
		if(useDataset && StringUtils.isNotBlank(dataset)){
			Dataset ds=context.getDataset(dataset);
			if(ds==null){
				throw new DatasetUndefinitionException(dataset);
			}
			//需要对收费科目做分组处理
			boolean isNeedGroup = false;
			StringBuilder meterReading = new StringBuilder("<optgroup label='抄表'>");
			StringBuilder unMeterReading = new StringBuilder("<optgroup label='非抄表'>");
			if(StringUtils.equals(RangeDateUtils.CHARGE_ITEM,this.getLabel())){
				isNeedGroup = true;
			}
			for(Object obj:ds.getData()){
				Object label=Utils.getProperty(obj, labelField);
				Object value=Utils.getProperty(obj, valueField);
				String selected=value.equals(pvalue) ? "selected" : "";
				String option = "<option value='"+value+"' "+selected+">"+label+"</option>";
				if(isNeedGroup){
					if (RangeDateUtils.IS_METER_READING.equals(Utils.getProperty(obj, "ifMeterReading"))) {
						meterReading.append(option);
					} else {
						unMeterReading.append(option);
					}
				}else{
					sb.append(option);
				}
			}
			if(isNeedGroup){
				meterReading.append("</optgroup>");
				unMeterReading.append("</optgroup>");
				sb.append(meterReading).append(unMeterReading);
			}
		}else{
			for(Option option:options){
				String value=option.getValue();
				String selected=value.equals(pvalue) ? "selected" : "";
				sb.append("<option value='"+value+"' "+selected+">"+option.getLabel()+"</option>");
			}
			/*if(pvalue.equals("")){
				sb.append("<option value='' selected></option>");
			}*/
		}

		sb.append("</select>");
		return sb.toString();
	}
	@Override
	public String initJs(RenderContext context) {
		String name=getBindParameter();
		StringBuilder sb=new StringBuilder();
		sb.append("formElements.push(");
		sb.append("function(){");
		sb.append("if(''==='"+name+"'){");
		sb.append("alert('列表框未绑定查询参数名，不能进行查询操作!');");
		sb.append("throw '列表框未绑定查询参数名，不能进行查询操作!'");
		sb.append("}");
		sb.append("return {");
		sb.append("\""+name+"\":");		
		sb.append("$('#"+context.buildComponentId(this)+"').val()");
		sb.append("}");
		sb.append("}");
		sb.append(");$('#"+context.buildComponentId(this)+"').selectpicker('refresh');");
		return sb.toString();
	}
	public boolean isUseDataset() {
		return useDataset;
	}
	public String getDataset() {
		return dataset;
	}
	public void setDataset(String dataset) {
		this.dataset = dataset;
	}
	public String getLabelField() {
		return labelField;
	}
	public void setLabelField(String labelField) {
		this.labelField = labelField;
	}
	public String getValueField() {
		return valueField;
	}
	public void setValueField(String valueField) {
		this.valueField = valueField;
	}
	public void setUseDataset(boolean useDataset) {
		this.useDataset = useDataset;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public List<Option> getOptions() {
		return options;
	}
}
