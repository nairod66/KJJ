<%-- 
    Document   : login
    Created on : 2016-12-02, 11:04:13
    Author     : jycy
--%>
<div id="login">
    <div class="container">
        <h1 class="text-xs-center Titre-main">Login</h1>
        <form id="form-login">
            <div class="form-group row">
              <label for="login-username" class="col-xs-2 col-form-label">Username</label>
              <div class="col-xs-10">
                <input class="form-control" name="username" type="text" placeholder="Username" id="login-username">
              </div>
            </div>
           
            <div class="form-group row">
              <label for="login-password" class="col-xs-2 col-form-label">Password</label>
              <div class="col-xs-10">
                <input class="form-control" name="password" type="password" placeholder="password" id="login-password">
              </div>
            </div>
            <input id="login-submit" type="button" class="btn btn-lg btn-outline-danger btn-block" value="connecter">
        </form>
    </div>
</div>