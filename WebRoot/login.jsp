<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>login</title>
<link href="./Wopop_files/style_log.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="./Wopop_files/style.css" />
<link rel="stylesheet" type="text/css"
	href="./Wopop_files/userpanel.css" />
<link rel="stylesheet" type="text/css"
	href="./Wopop_files/jquery.ui.all.css" />
	<script type="text/javascript" src="/js/login.js"></script>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>

</head>

<body class="login" mycollectionplug="bind">
	<div class="login_m">
		<div class="login_logo">
			<img src="./Wopop_files/logo.png" width="196" height="46" />
		</div>
		<div class="login_boder">
			<form action="/login.action" method="post">
				<div class="login_padding" id="login_model">

					<h2>USERNAME</h2>
					<label> <input type="text" id="username" name="userName"
						class="txt_input txt_input2"
						onfocus="if (value ==&#39;Your name&#39;){value =&#39;&#39;}"
						onblur="if (value ==&#39;&#39;){value=&#39;Your name&#39;}"
						value="Your name" />
					</label>
					<h2>PASSWORD</h2>
					<label> <input type="password" name="password" id="userpwd"
						class="txt_input"
						onfocus="if (value ==&#39;******&#39;){value =&#39;&#39;}"
						onblur="if (value ==&#39;&#39;){value=&#39;******&#39;}"
						value="******" />
					</label>




					<p class="forgot">
						<a id="iforget" href="javascript:void(0);">Forgot your
							password?</a>
					</p>
					<div class="rem_sub">
						<div class="rem_sub_l">
							<input type="checkbox" name="checkbox" id="save_me" /> <label
								for="checkbox">Remember me</label>
						</div>
						<label> <input type="submit" class="sub_button"
							name="button" id="button" value="SIGN-IN" style="opacity: 0.7;" />
						</label>
					</div>
				</div>
			</form>
			
			<!-- <br />

				<h1>Forgot password</h1>
				<br />
				<div class="forget_model_h2">(Please enter your registered
					email below and the system will automatically reset users’ password
					and send it to user’s registered email address.)</div>
				<label> <input type="text" id="usrmail"
					class="txt_input txt_input2" />
				</label> 


				<div class="rem_sub">
					<div class="rem_sub_l"></div>
					<label> <input type="submit" class="sub_buttons"
						name="button" id="Retrievenow" value="Retrieve now"
						style="opacity: 0.7;" /> <input type="submit" class="sub_button"
						name="button" id="denglou" value="Return" style="opacity: 0.7;" />

					</label>
				</div>-->
		</div>

		<!--login_padding  Sign up end-->
	</div>
	<!--login_boder end-->
	
	<!--login_m end-->
	<br />
	<br />
	
</body>
</html>
