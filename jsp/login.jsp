<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MBS_ProtoType</title>
</head>
<style type="text/css">

        .login_container{
            width: 380px; 
            height: 380px; 
            margin: auto; 
            border-radius: 3px; 
            box-shadow: 0px 0px 10px #000;
        }

        .form_container{
            width: 300px;
            margin: auto;
        }

        .form_title_div{
            margin: auto; 
            text-align: center;
        }

        .form_title_p{
            font-weight: bold; 
            font-size: 22px; 
            display: inline-block; 
            padding-top: 8px;
        }

        .form_input{
            width: 98%; 
            height: 30px; 
            border: 1px solid; 
            border-radius: 3px; 
            border-color: gray;
        }

        .form_item_name{
            color: gray;
        }

        .form_text_alert{
            height: 20px;
        }

        .form_text_alert_padding{
            padding-bottom: 10px;
        }

        .form_submit_button{
            width: 100%; 
            height: 44px; 
            background-color: rgb(83, 154, 236); 
            border: 1px; 
            border-radius: 3px; 
            color: white; 
            font-size: 17px; 
            font-weight: 500;
        }
        .form_submit_button2{
            width: 30%; 
            height: 20px; 
            background-color: rgb(83, 154, 236); 
            border: 1px; 
            border-radius: 3px; 
            color: white; 
            font-size: 17px; 
            font-weight: 500;
            margin-top: 20px;
            margin-left: 104px;
        }
    }
</style>
<body>
<div id="container" class="main_container">
        <div style="padding: 20px;"></div>
        <div class="login_container">
            <div class="form_container">
                <form name="login_form" action="loginAction.jsp" method="post">
                    <div class="form_title_div">
                        <p class="form_title_p">LogIn</p>
                    </div>
                    <div>
                        <div>
                            <a class="form_item_name">userID</a>
                        </div>
                        <div>
                            <input type="text" name="userID" placeholder="ID" class="form_input"/>
                        </div>
                        <div class="form_text_alert_padding">
                            <div id="alert_username" class="form_text_alert"></div>
                        </div>
                    </div>

                    <div>
                        <div>
                            <a class="form_item_name">Password</a>
                        </div>
                        <div>
                            <input type="password" name="password" placeholder="Enter password" class="form_input" />
                        </div>
                        <div class="form_text_alert_padding">
                            <div id="alert_password" class="form_text_alert"></div>
                        </div>
                    </div>

                    <div style="height: 10px;"></div>
                    <div>
                        <button type="submit" class="form_submit_button">LogIn
                        </button>
                    </div>
                </form>
                <button class="form_submit_button2" type="button" onclick="location.href='join.jsp'">Join</button>
            </div>
        </div>
    </div>
    
</body>
</html>