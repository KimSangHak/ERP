<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/MyPageMaster">

	<layout:put block="mainbody">

                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>쪽지리스트</li>
                        <li>받은 쪽지</li>
                    </ul>
                    <h2>받은 쪽지</h2>
                    <div class="btnArea"><a href="javascript:PopWin('00_pop_message.html','600','700','no');" class="btn_line_blue">쪽지발송</a>
                    </div>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                   <!-- 테이블 콘텐츠 -->
                    <div class="listArea">

                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:100px;" />
                                <col style="width: ;" />
                                <col style="width:120px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>발송자</th>
                                        <th>내용</th>
                                        <th>발송시간</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<tr>
                                    	<td>홍길동</td>
                                        <td id="taLeft">고객사의 요청으로 중공감속기 사양서를 반드시 고객사로 송부해야 합니다. 각 부서 담당자들은 유의 하시기 바랍니다.</td>
                                        <td>16.09.27:13:02</td>
                                    </tr>
                                    <tr>
                                    	<td>홍길동</td>
                                        <td id="taLeft">고객사의 요청으로 중공감속기 사양서를 반드시 고객사로 송부해야 합니다. 각 부서 담당자들은 유의 하시기 바랍니다.</td>
                                        <td>16.09.27:13:02</td>
                                    </tr>
                                    <tr>
                                    	<td>홍길동</td>
                                        <td id="taLeft">고객사의 요청으로 중공감속기 사양서를 반드시 고객사로 송부해야 합니다. 각 부서 담당자들은 유의 하시기 바랍니다.</td>
                                        <td>16.09.27:13:02</td>
                                    </tr>
                                    <tr>
                                    	<td>홍길동</td>
                                        <td id="taLeft">고객사의 요청으로 중공감속기 사양서를 반드시 고객사로 송부해야 합니다. 각 부서 담당자들은 유의 하시기 바랍니다.</td>
                                        <td>16.09.27:13:02</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->


                </div>
                <!-- //서브 콘텐츠 -->
	
	
	</layout:put>
	<layout:put block="BodyScriptBlock2">
	
		<script type="text/javascript">
		
			$(document).ready(function(){
				
			});
		
		</script>
			
	</layout:put>
</layout:extends>