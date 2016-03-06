<%--
  Created by IntelliJ IDEA.
  User: danielkarkee
  Date: 3/1/16
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading text-center">
                        Sign in
                    </div>
                    <div class="panel-body">
                        <form novalidate>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-user"></i></div>
                                    <input type="text" class="form-control" placeholder="Username" required>
                                </div>
                                <br/>
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-lock"></i></div>
                                    <input type="password" class="form-control" placeholder="Password" required>
                                </div>
                                <br/>
                                <div class="text-center">
                                    <button class="btn btn-primary">
                                        <i class="fa fa-sign-in"></i> Log In
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
