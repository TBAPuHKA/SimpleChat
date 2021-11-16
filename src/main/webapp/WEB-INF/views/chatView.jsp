<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@include file="/source/includes/header.jsp"%>

        <div class="container bootstrap snippets bootdeys">
            <div class="wrapper">
                <div class="panel" id="chat">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <form action="./chat" method="post">
                                <div class="input-group">
                                   we know you as:<br>
                                    ${user.name}
                                    <input type="hidden" class="form-control" name="logOut" value="logOut">
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="submit">Logout</button>
                                    </span>
                                </div>
                            </form>
                            <hr>
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="chats">

<script>
                            user="${user.name}";
                            messageList = ${messageList};

                            for (i=0;i<messageList.length;i++){
                                document.write('<div class="');

                                if(user==messageList[i].userName){
                                    document.write('chat');
                                } else {
                                    document.write('chat chat-left');
                                }

                                document.write('"><div class="chat-body"><div class="chat-content">' + messageList[i].content + '<br>by <b>' + messageList[i].userName +'</b></div></div></div>');
                            }
</script>

                        </div>
                    </div>
                    <div class="panel-footer">
                        <hr>
                        <form action="./chat" method="post">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Say something" name="sendMessage" id="sendMessage">
                                <span class="input-group-btn">
                                    <button class="btn btn-primary" type="submit">Send</button>

                                </span>
                            </div>
                        </form>
                        <br>
                        <form action="./chat" method="post">
                            <div class="input-group">
                                <input type="hidden" class="form-control" name="refresh" value="refresh">
                                <span class="input-group-btn">
                                        <button class="btn btn-primary" type="submit">REFRESH</button>
                                    </span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

<%@include file="/source/includes/footer.jsp"%>