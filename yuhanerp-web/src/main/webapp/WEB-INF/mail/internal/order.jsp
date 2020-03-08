<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div>
안녕하세요.<br/>
작업지시서 배포드립니다.<br/>

감사합니다.<br/>
</div>
		<div style="border:1px solid #d8d8d8; padding:15px; margin-top:10px;">
                    <!-- 테이블 콘텐츠  -->
                <div>
                	<div>
                    	<h4 style="font-size:13px; color:#777; font-weight:400; margin:6px 0;">${item.orderTypeName } 작업 지시</h4>
                        <h3 style="font-size:15px; color:#555; font-weight:500; margin:6px 0;">${item.device }(<fmt:formatDate value="${today }" pattern="yyyy-MM-dd"/>)</h3>
                    </div>
                    <div>
                        <table style="border-top:none;border-top:1px solid #aaaaaa;" width="100%">
                            <caption> </caption>
                            <colgroup span="2">
                            <col style="width:20%;" />
                            <col style="width:30%;" />
                            <col style="width:20%;" />
                            <col style="width:30%;" />
                            </colgroup>
                            <tr style="border-bottom:1px solid #d8d8d8;">
                                <th style="background:#fafafb; color:#333; font-size:14px; font-weight:400; line-height:20px; height:35px; padding:5px 10px; vertical-align:middle; text-align:left; border-right:1px solid #dedede;">Order No</th>
                                <td style="line-height:20px; height:35px; padding:5px 10px; text-align:left; vertical-align:middle; border-right:1px solid #dedede;">${item.orderNo }(${item.customerName })</td>
                                <th style="background:#fafafb; color:#333; font-size:14px; font-weight:400; line-height:20px; height:35px; padding:5px 10px; vertical-align:middle; text-align:left; border-right:1px solid #dedede;">수량</th>
                                <td style="line-height:20px; height:35px; padding:5px 10px; text-align:left; vertical-align:middle; border-right:1px solid #dedede;">${item.quantity }</td>
                            </tr>
                            <tr style="border-bottom:1px solid #d8d8d8;">
                                <th style="background:#fafafb; color:#333; font-size:14px; font-weight:400; line-height:20px; height:35px; padding:5px 10px; vertical-align:middle; text-align:left; border-right:1px solid #dedede;">납품일</th>
                                <td style="line-height:20px; height:35px; padding:5px 10px; text-align:left; vertical-align:middle; border-right:1px solid #dedede;"><fmt:formatDate value="${item.installDate }" pattern="yyyy-MM-dd"/></td>
                                <th style="background:#fafafb; color:#333; font-size:14px; font-weight:400; line-height:20px; height:35px; padding:5px 10px; vertical-align:middle; text-align:left; border-right:1px solid #dedede;">출도예정</th>
                                <td style="line-height:20px; height:35px; padding:5px 10px; text-align:left; vertical-align:middle; border-right:1px solid #dedede;"><fmt:formatDate value="${item.designDate }" pattern="yyyy-MM-dd"/></td>
                            </tr>
                            <tr style="border-bottom:1px solid #d8d8d8;">
                                <th style="background:#fafafb; color:#333; font-size:14px; font-weight:400; line-height:20px; height:35px; padding:5px 10px; vertical-align:middle; text-align:left; border-right:1px solid #dedede;">가공예정</th>
                                <td style="line-height:20px; height:35px; padding:5px 10px; text-align:left; vertical-align:middle; border-right:1px solid #dedede;"><fmt:formatDate value="${item.processDate }" pattern="yyyy-MM-dd"/></td>
                                <th style="background:#fafafb; color:#333; font-size:14px; font-weight:400; line-height:20px; height:35px; padding:5px 10px; vertical-align:middle; text-align:left; border-right:1px solid #dedede;">검사예정</th>
                                <td style="line-height:20px; height:35px; padding:5px 10px; text-align:left; vertical-align:middle; border-right:1px solid #dedede;"><fmt:formatDate value="${item.testDate }" pattern="yyyy-MM-dd"/></td>
                            </tr>
                            <tr style="border-bottom:1px solid #d8d8d8;">
                                <th style="background:#fafafb; color:#333; font-size:14px; font-weight:400; line-height:20px; height:35px; padding:5px 10px; vertical-align:middle; text-align:left; border-right:1px solid #dedede;">조립예정</th>
                                <td style="line-height:20px; height:35px; padding:5px 10px; text-align:left; vertical-align:middle; border-right:1px solid #dedede;"><fmt:formatDate value="${item.assemblyDate }" pattern="yyyy-MM-dd"/></td>
                                <th style="background:#fafafb; color:#333; font-size:14px; font-weight:400; line-height:20px; height:35px; padding:5px 10px; vertical-align:middle; text-align:left; border-right:1px solid #dedede;">배선/PGM</th>
                                <td style="line-height:20px; height:35px; padding:5px 10px; text-align:left; vertical-align:middle; border-right:1px solid #dedede;"><fmt:formatDate value="${item.programDate }" pattern="yyyy-MM-dd"/></td>
                            </tr>
                            <tr style="border-bottom:1px solid #d8d8d8;">
                                <th style="background:#fafafb; color:#333; font-size:14px; font-weight:400; line-height:20px; height:35px; padding:5px 10px; vertical-align:middle; text-align:left; border-right:1px solid #dedede;">비고</th>
                                <td colspan="3" style="line-height:20px; height:35px; padding:5px 10px; text-align:left; vertical-align:middle; border-right:1px solid #dedede;">${item.note }</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <!-- 테이블 콘텐츠 -->
		</div>                
		<br><br>
<div id="signature">
	<table style="width:100%" border="0" cellspacing="0" cellpadding="0">        
		<tbody>        
			<tr>            
				<td width="60" height="60" style="padding-right:18px; vertical-align : bottom">
					<img id="" style="border-radius:50%;width:60px;height:60px" src="/logo.png">
				</td>            
				<td align="left" valign="top">                
					<table style="width:100%" border="0" cellspacing="0" cellpadding="0">                    
						<tbody>                    
							<tr>                        
								<td style="padding-bottom:5px">
									<p><span style="font-family:&quot;\00b9d1\00c740  \00ace0\00b515&quot;,&quot;Malgun Gothic&quot;,&quot;\00b3cb\00c6c0&quot;,dotum,sans-serif;font-size:14px;font-weight:bold">
										(주)유한엔씨아이 ${sender.deptName}&nbsp;${sender.name }&nbsp;&nbsp;${sender.position }
										</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<wbr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									</p>                        
								</td>                    
							</tr>                    
							<tr>                        
								<td>
									<p style="color:rgb(136,136,136);line-height:17px;font-family:&quot;\00b9d1\00c740  \00ace0\00b515&quot;,&quot;Malgun Gothic&quot;,&quot;\00b3cb\00c6c0&quot;,dotum,sans-serif;font-size:12px">                                
										<span style="color:rgb(17,17,17);font-weight:bold">t </span>032-816-8781
										<span style="padding:0px 6px;color:rgb(215,215,215)">|</span>
										<span style="color:rgb(17,17,17);font-weight:bold">m</span>  ${sender.handPhone }
										<span style="padding:0px 6px;color:rgb(215,215,215)">|</span>
										<span style="color:rgb(17,17,17);font-weight:bold">d</span> ${sender.directPhone }
										<a href="mailto:mail@daum.net%7Ce" target="_blank">
											<span style="padding:0px 6px;color:rgb(215,215,215)">|</span>
										</a>
										<span style="color:rgb(17,17,17);font-weight:bold"><a>e</a></span>
										&nbsp;<a href="mailto:${sender.email }" target="_blank">${sender.email }</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<wbr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									</p>                        
								</td>                    
							</tr>                    
							<tr>                        
								<td>                            
									<p style="color:rgb(136,136,136);line-height:17px;font-family:&quot;\00b9d1\00c740  \00ace0\00b515&quot;,&quot;Malgun Gothic&quot;,&quot;\00b3cb\00c6c0&quot;,dotum,sans-serif;font-size:12px">                                
									<span style="color:rgb(17,17,17);font-weight:bold">a</span> 인천시 남동구&nbsp;<a href="https://maps.google.com/?q=%EB%82%A8%EB%8F%99%EC%84%9C%EB%A1%9C83%EB%B2%88%EA%B8%B8+49&amp;entry=gmail&amp;source=g">남동서로83번길 49</a>(고잔동 686-1)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<wbr>&nbsp;&nbsp;&nbsp; 
									</p>                        
								</td>                    
							</tr>                    
						</tbody>
					</table>            
				</td>        
			</tr>        
		</tbody>    
	</table><p><br></p>
</div>
		