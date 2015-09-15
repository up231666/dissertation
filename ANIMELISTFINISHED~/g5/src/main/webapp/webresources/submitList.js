
// When user clicks button to generate composite image

$(document).ready(function () {
    $('#button').click(function () {

        var array1 = [];

        // For each instance of class in listbox 2, 
        // add the data contained in the src attribute of that element to an array
        // Send to image generation servlet

        $('#sortable2 .selectedItemImg').each(function () {

            array1.push($(this).attr('src'));
        });

        $.ajax({
            url: 'generate',
            type: 'POST',
            contentType: 'text/plain',
            dataType: 'text',
            data: array1.join('\n'),
            success: function (data) {
                
                // Data here is equal to the URL of our uploaded generated image
                // Print this to end user by appending it after a div as a hyperlink with a href value equal to the data
                // Our user can now click on the hyperlink and see their generated composite image that has been uploaded to Imgur 

                $("#displayGeneratedURL").after("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=" + data + " target='_blank'>Image successfully generated!</a>");

            }
        }
        );
    });
    return false;
}
);


