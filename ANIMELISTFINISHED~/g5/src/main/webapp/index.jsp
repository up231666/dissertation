<%@page language ="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Anime List Creator</title>

        <script src="webresources/jquery-2.1.3.min.js"></script>
        <script src="webresources/basic.js"></script>
        <script src="webresources/submitList.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <style>

            body {
                background-color: #A75938; color: #551A02;
            }

            #sortable1 {
                color: #551A02; list-style-type: none; padding: 0px; width: 702px; border-style: solid; border-width: 2px; min-height: 230px; float: left; background-color: #FFD4C2; max-height: 1240px; overflow: auto;
            }

            #sortable2 {
                color: #551A02; list-style-type: none; padding: 0px; width: 698px; margin-left: 30px; border-style: solid; border-width: 2px; min-height: 230px; float: left; background-color: #FABEA4; 
            }


            #sortable1 li, #sortable2 li {
                color: #551A02;
                margin: 10px 0px 45px 10px; padding: 0px; width: 162px; height: 225px; font-size: 12px; text-align: center; float: left; color: white; border-style: none; font-family: Geneva,Tahoma,Verdana,sans-serif;
            }

            #headerplaceholder{
                background-color: #551A02;
            }

            #headingtext{
                color: white; font-family: Geneva,Tahoma,Verdana,sans-serif; font-size: 24px;
            }

            #makeasearchtext{
                color: white; font-family: Geneva,Tahoma,Verdana,sans-serif; font-size: 16px;
            }

            #displayGeneratedURL{
                position: absolute; color:  white; font-family: Geneva,Tahoma,Verdana,sans-serif; font-size: 12px;
            }

            a{
                color:  white; font-family: Geneva,Tahoma,Verdana,sans-serif; font-size: 16px;
            }
        </style>

        
        <!--jQuery UI sortable list-->
        <script>
            $(function () {
                $("#sortable1, #sortable2").sortable({
                    connectWith: ".connectedSortable"
                }).disableSelection();
            });
        </script>

    </head>

    <body >
        <div id="headerplaceholder">
            </br>          </br>

            <div id="headingtext">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Anime List Creator</div>
            </br>

            <form id="searchForm">
                <label id="makeasearchtext" for="searchQuery">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Make a search&nbsp;&nbsp;&nbsp;</label>
                <input type="text" id="searchQuery" name="searchQuery"/>
                <input type="submit" value="Search"/>
            </form>

            </br>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <button id="button" name="button">Save List</button>

            <!--Display our generated hyperlink here-->
            <div id="displayGeneratedURL"></div>
            </br>  </br>  
        </div>

        <div id="searchContainer1"></div>

        <hr/>

        <ul id="sortable1" class="connectedSortable">
        </ul>

        <ul id="sortable2" class="connectedSortable">
        </ul>
</body>
</html>
