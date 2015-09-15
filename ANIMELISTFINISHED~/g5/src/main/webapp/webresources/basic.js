

// Send search string from form to our search servlet

$(document).ready(function () {
    $('#searchForm').submit(function () {

        $.ajax({
            url: 'search',
            type: 'POST',
            dataType: 'text',
            data: $('#searchForm').serialize(),
            success: function (data) {
                
                // Empty listbox 1 before filling it with new search results
                
                $("#sortable1").empty();
                
                // Receive JSON object from servlet
                // Parse this and use jQuery to insert new HTML elements into 
                // listbox 1 with properties parsed from the data 
                // This displays our search results to user in the form of 
                // anime images and corresponding text as members of the interactive listbox
                // Format these to appropriate dimensions

                $.each(JSON.parse(data), function (listID, mapData) {

                    $("#sortable1").append(
                            "<li class=userList><div class=selectedItemId id=" + mapData.id + "><img class=selectedItemImg src="
                            + mapData.url + " alt=" + mapData.name
                            + " style='width:162px;height:225px'></img><div id='animeTitle'>"
                            + mapData.name + "</div></div></li>");
                });
            }
        });
        return false;
    });
});