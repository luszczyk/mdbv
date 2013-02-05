<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="net.luszczyk.mdbv.common.model.DataBase" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type"
          content="application/xhtml+xml; charset=UTF-8"/>
    <meta name="author" content="Lukasz Luszczynski - www.luszczyk.net"/>
    <meta name="robots" content="index, follow"/>
    <link rel="stylesheet" type="text/css" media="screen"
          href="/web/resources/css/style.css"/>
    <link rel="stylesheet" type="text/css" media="screen"
          href="/web/resources/css/openlayer.css"/>
    <link rel="stylesheet" type="text/css" media="screen"
          href="/web/resources/css/jquery.toastmessage.css"/>
    <link rel="stylesheet" type="text/css" media="screen"
          href="/web/resources/css/jquery-ui-1.8.22.custom.css"/>
    <link rel="stylesheet" type="text/css" media="screen"
          href="/web/resources/css/prettyPhoto.css"/>
    <script type="text/javascript" src="/web/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/web/resources/js/jquery.prettyPhoto.js"></script>
    <script type="text/javascript"
            src="/web/resources/js/jquery.toastmessage.js"></script>
    <script type="text/javascript"
            src="/web/resources/js/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript"
            src="/web/resources/js/jquery.autosize-min.js"></script>
    <script type="text/javascript" src="/web/resources/js/OpenLayers.js"></script>

    <script type="text/javascript">
        // tell OpenLayers where the control images are
        // remember the trailing slash
        OpenLayers.ImgPath = "/web/resources/js/img/";
    </script>

    <script type="text/javascript">

        function connTest(dbform) {
            if ($('#dbhost').val() == "" && $('#dbuser').val() == ""
                    && $('#dbport').val() == "" && $('#dbpass').val() == "") {
                $()
                        .toastmessage('showNoticeToast',
                        'Fill all fields in formular !');
            } else {
                var dataBase = new Object();
                dataBase.host = $('#dbhost').val();
                dataBase.user = $('#dbuser').val();
                dataBase.port = $('#dbport').val();
                dataBase.pass = $('#dbpass').val();
                dataBase.name = $('#dbname').val();

                $.ajax({
                    url:"/web/db/test.json",
                    type:"POST",
                    headers:{
                        'Accept':'application/json',
                        'Content-Type':'application/json'
                    },
                    data:JSON.stringify(dataBase),
                    success:function (res) {
                        if (res.status == 0) {
                            $().toastmessage({
                                text:res.msg,
                                sticky:true,
                                stayTime:6000,
                                position:'top-right',
                                type:'success'
                            });
                            dbform.submit();
                            return true;
                        } else {
                            $().toastmessage({
                                text:res.msg,
                                sticky:true,
                                stayTime:6000,
                                position:'top-right',
                                type:'error'
                            });
                            return false;
                        }
                    },
                    error:function (e) {
                        $().toastmessage({
                            text:e,
                            stayTime:6000,
                            sticky:true,
                            position:'top-right',
                            type:'error'
                        });
                        return false;
                    }
                });
            }
        }
        ;

        var request_in_process = false;

        function dbConnect(dbform) {

            if (!request_in_process) {

                if ($('#dbhost').val.length < 1 || $('#dbuser').val.length < 1
                        || $('#dbname').val.length < 1 || $('#dbport').val.length < 1
                        || $('#dbpass').val.length < 1) {
                    $().toastmessage('showNoticeToast', 'Fill all fields in formular !');

                } else {
                    request_in_process = true;
                    $('#spinner').show();
                    var dataBase = new Object();
                    dataBase.host = $('#dbhost').val();
                    dataBase.user = $('#dbuser').val();
                    dataBase.port = $('#dbport').val();
                    dataBase.pass = $('#dbpass').val();
                    dataBase.name = $('#dbname').val();
                    dataBase.type = $('#dbtype').val();

                    $.ajax({
                        url:"/web/db/connect.json",
                        type:"POST",
                        headers:{
                            'Accept':'application/json',
                            'Content-Type':'application/json'
                        },
                        data:JSON.stringify(dataBase),
                        success:function (res) {
                            $('#spinner').hide();
                            if (res.status == 0) {
                                $().toastmessage('showToast', {
                                    text:res.msg,
                                    sticky:true,
                                    stayTime:6000,
                                    position:'top-right',
                                    type:'success'
                                });
                                window.location.replace("/web/query/index");
                            } else {
                                $('#spinner').hide();
                                $().toastmessage('showToast', {
                                    text:res.msg,
                                    sticky:true,
                                    stayTime:6000,
                                    position:'top-right',
                                    type:'error'
                                });
                            }
                            request_in_process = false;
                        },
                        error:function (e) {
                            $('#spinner').hide();
                            $().toastmessage('showToast', {
                                text:e,
                                sticky:true,
                                stayTime:6000,
                                position:'top-right',
                                type:'error'
                            });
                            request_in_process = false;
                        }
                    });
                }
            }
        }
        ;

        function dbDisconnect() {

            if (!request_in_process) {


                    request_in_process = true;
                    $('#spinner').show();

                    $.ajax({
                        url:"/web/db/disconnect.json",
                        type:"POST",
                        headers:{
                            'Accept':'application/json',
                            'Content-Type':'application/json'
                        },
                        success:function (res) {
                            $('#spinner').hide();
                            if (res.status == 0) {
                                $().toastmessage('showToast', {
                                    text:res.msg,
                                    sticky:true,
                                    stayTime:6000,
                                    position:'top-right',
                                    type:'success'
                                });
                                window.location.replace("/web/index");
                            } else {
                                $('#spinner').hide();
                                $().toastmessage('showToast', {
                                    text:res.msg,
                                    sticky:true,
                                    stayTime:6000,
                                    position:'top-right',
                                    type:'error'
                                });
                            }
                            request_in_process = false;
                        },
                        error:function (e) {
                            $('#spinner').hide();
                            $().toastmessage('showToast', {
                                text:e,
                                sticky:true,
                                stayTime:6000,
                                position:'top-right',
                                type:'error'
                            });
                            request_in_process = false;
                        }
                    });
                }
        };

    </script>
    <title>Multimedia Database Viewer - ${h.title}</title>
