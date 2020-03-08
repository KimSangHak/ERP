<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div>
안녕하세요.<br/>

유한NCI&nbsp;${sender.deptName}&nbsp;${sender.name }&nbsp;${sender.position }&nbsp;입니다. <br/>
${title } 요청 드립니다.<br/>

감사합니다.<br/>
<br/>
<br/>
</div>
            <!-- 메일발송 내역 -->
            <div class="mail_con">
                
                <c:forEach items="${data }" var="item">
                <!-- 테이블 콘텐츠 -->
                <div class="popList">
                    <div class="popListTit">
                        <h4 style="font-size:13px; color:#777; font-weight:400; margin:6px 0;">${title2 } 요청 작성</h4>
                        <h3 style="font-size:15px; color:#555; font-weight:500; margin:6px 0;">Device : ${item[0].device }</h3>
                        <h4 style="font-size:13px; color:#777; font-weight:400; margin:6px 0;">Order No : [${item[0].jobOrderNo }]</h4>
                    </div>
                    
                    
                    <c:choose>
                            <c:when test="${title eq '견적 의뢰'}">
                                 <!-- 게시판 -->
                                <div>
                                    <table style="margin-top:10px; border-top:1px solid #aaaaaa; right:20px;" width="100%">
                                        <caption> </caption>
                                        <colgroup span="2">
                                        <col style="width:7%;" />
                                        <col style="width:9%;" />
                                        <col style="width:9%;" />
                                        <col style="width:6%;" />
                                        <col style="width:6%;" />
                                        <col style="width:6%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:6%;" />
                                        <col style="width:12%;" />
                                        </colgroup>
                                        <tr style="border-bottom:1px solid #d8d8d8;">
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">UNIT</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">Description</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">Model No/Size</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">Maker</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">Provider</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">재질</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">1set qty</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">code</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">comment</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">remark</th>
                                        </tr>
                                        <c:forEach items="${item }" var="line">
                                        <tr style="border-bottom:1px solid #d8d8d8;">
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.unitNo } ${line.seq }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.description }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.modelNo }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.maker }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.partnerName }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.material }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.quantity }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.code }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.comment }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.remark }</td>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                    
                    <!-- //게시판 -->
                             </c:when>
                             <c:when test="${title eq '발주'}">
                                <div>
                                    <table  style="margin-top:10px; border-top:1px solid #aaaaaa; right:20px;" width="100%">
                                        <caption> </caption>
                                        <colgroup span="2">
                                        <col style="width:7%;" />
                                        <col style="width:9%;" />
                                        <col style="width:9%;" />
                                        <col style="width:6%;" />
                                        <col style="width:6%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:6%;" />
                                        <col style="width:6%;" />
                                        <col style="width:6%;" />
                                        <col style="width:6%;" />
                                        <col style="width:7%;" />
                                        <col style="width:13%;" />
                                        
                                        </colgroup>
                                        <tr style="border-bottom:1px solid #d8d8d8;">
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">UNIT</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">Description</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">Model No/Size</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">Maker</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">Provider</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">QTY</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">CODE</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">COMMENT</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">단가</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">공급가</th>
                                            <th style="background:#fafafb; color:#333; font-size:15px; font-weight:400; line-height:20px; padding:15px 5px; vertical-align:middle; text-align:center; border-right:1px solid #dedede;">납기일</th>
                                   
                                        </tr>
                                        <c:forEach items="${item }" var="line">
                                        <fmt:parseNumber var="qty" value="${line.quantity}" integerOnly="true"/>
                                        <fmt:parseNumber var="price" value="${line.issuePrice}" integerOnly="true"/>
                                        <fmt:parseNumber var="Sum" value="${qty*price}" integerOnly="true"/>
                                        <tr style="border-bottom:1px solid #d8d8d8;">
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.unitNo } ${line.seq }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.description }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.modelNo }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.maker }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.partnerName }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.quantity }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.code }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;">${line.comment }</td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;"><fmt:formatNumber value="${line.issuePrice}" pattern="#,###" /></td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;"><fmt:formatNumber value="${Sum}" pattern="#,###" /></td>
                                            <td style="line-height:18px; padding:5px 3px; text-align:center; height:35px;  vertical-align:middle; font-size:13px; font-weight:400; color:#777; border:1px solid #dedede; letter-spacing:-0.031em;"><fmt:formatDate value="${line.receiveDate}" pattern="yyyy-MM-dd"/></td>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                             </c:when>
                   </c:choose>
                    
                   
                    
                    
                </div>
                <!-- 테이블 콘텐츠 -->
                
                </c:forEach>
                
          
                
                
                
                <!-- 메일본문내용 -->
                <!-- 
                <div class="mail_text">
                    <textarea rows="10">고객사의 요청으로 중공감속기 사양서를 반드시 고객사로 송부해야 합니다. 각 부서 담당자들은 유의 하시기 바랍니다.</textarea>
                </div>
                -->
                <!-- //메일본문내용 -->
                
                <!-- 주의사항 -->
                <div style="margin-top:10px; font-weight:400;">
                    <h6 style="color:#eb6d3b; padding-bottom:5px;">※ 매입업체 요청사항 ※</h6>
                    <ol>
                        <li style="padding-bottom:5px;">1. 세금계산서, 거래명세서 발행시, <span>ORDER NO.</span> 필히 기재.</li>
                        <li style="padding-bottom:5px;">2. 납품시, 거래명세서 필히 공급. (계산서 내역 검토시, 거래명세서 확인)</li>
                    </ol>
                    <p><span style="color:#eb6d3b;">상기 사항 미 적용시, 결재 승인 불가합니다.</span></p>
                </div>
                <!-- /주의사항 -->
                
            </div>
            <!-- //메일발송 내역 -->
        
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
        