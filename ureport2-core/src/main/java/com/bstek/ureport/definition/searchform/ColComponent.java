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

/**
 * @author Jacky.gao
 * @since 2017年10月23日
 */
public class ColComponent extends ContainerComponent{
	private int size;
	@Override
	public String toHtml(RenderContext context) {
		StringBuffer sb=new StringBuffer();
		sb.append("<div class='col-md-"+size+"' style='padding-left:2px;padding-right:2px'");
		Object gridComponent=context.getMetadata(GridComponent.KEY);
		if(gridComponent!=null){
			GridComponent gc=(GridComponent)gridComponent;
			if(gc.isShowBorder()){
				String border="border:solid "+gc.getBorderWidth()+"px "+gc.getBorderColor()+"";
				sb.append(" style='"+border+";padding:10px'");
			}
		}
		sb.append(">");
		for(Component c:children){
			//TODO 目前是控制选择日期范围，特意修改样式
			if(c instanceof DateInputComponent && ((DateInputComponent) c).getBindParameter().endsWith(RangeDateUtils.START_DATE)){
				repalceWord(sb,"style='padding-left: 10px;width: 63%;padding-right: 0px;'");
			}else if(c instanceof DateInputComponent && ((DateInputComponent) c).getBindParameter().endsWith(RangeDateUtils.END_DATE)){
				repalceWord(sb,"style='padding: 0;display: inline-block;float: right;width: 37%;margin: 0;'");
			}
			sb.append(c.toHtml(context));
		}
		sb.append("</div>");
		return sb.toString();
	}
	private void repalceWord(StringBuffer sb,String finalReplaceWord){
		int replaceStart = sb.indexOf(RangeDateUtils.REPLACE_START_WORD);
		int replaceEnd = replaceStart + RangeDateUtils.REPALCE_END_WORD.length();
		sb.replace(replaceStart,replaceEnd,finalReplaceWord);
	}
	@Override
	public String initJs(RenderContext context) {
		StringBuilder sb=new StringBuilder();
		for(Component c:children){
			sb.append(c.initJs(context));
		}
		return sb.toString();
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getSize() {
		return size;
	}
	@Override
	public String getType() {
		return null;
	}
}
