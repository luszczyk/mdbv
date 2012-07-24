var pixDisplay = function() {
 var overlay = jQuery( '<div id="overlay" class="overlayClass"></div>');
 var links = null;
 var container = jQuery( '<div id="pixDisplay" style="display: none"></div>' );
 var target = jQuery( '<div class="target"></div>' ) ;
 var close =  jQuery( '<a href="#close" class="close">&times; Close</a>' );
 var currentImage = null;

 /* begin private */
 
 
function notThere(obj)
{
		
	
		if ((typeof obj == 'undefined') || (obj == null))
		{
			return true;
		}
		if ( obj instanceof Array && obj.length == 0)
		{
		
			return true;
		}
		return false;

}
 
 
 
 function resetContainer()
 {
   initHeight = 300;
   initWidth  = 400;
  var cssItems = 
    {
    'top' :Math.round((($(window).height()>window.innerHeight?window.innerHeight:$(window).height())- initHeight)/2)+jQuery(window).scrollTop()+'px',
    'left':Math.round(($(window).width()- initWidth)/2)+'px','margin-top':0,'margin-left':0,
	'width':initWidth,
	'height':initHeight
    } ;
 
    return cssItems;
 }
 
 
 	
/*

   fade in the overlay
   remove images from container
   load the image
   display the container

*/	
 function displayImage(url)
 {
     overlay.css({'top': jQuery(window).scrollTop()});
	 overlay.fadeIn('normal',function(){});
	//console.log("clicked "+url);
	//container.show().css(resetContainer());
	
	if( container.is( ':visible' ) ) {
            target.children().fadeOut( 'normal', function() {
            target.children().remove();
            loadImage( url );
            } );
        } else {
            target.children().remove();
            overlay.add( container ).fadeIn( 'normal',function(){
            loadImage( url );
            } );
        }
 } //displayImage(url)

 
 /*
 load an image based on the url
 
 */
 function loadImage(url)
 {
//	 console.log('scroll '+jQuery(window).scrollTop());
 if( container.is( '.loading' ) ) { return; }
        container.addClass( 'loading' );
        var img = new Image();
        img.onload = function() {
            img.style.display = 'none';
            var maxWidth = ( $( window ).width() - parseInt( container.css( 'padding-left' ),10 ) 
			  - parseInt( container.css( 'padding-right' ), 10 ) ) - 100;
            var maxHeight = ( $( window ).height() - parseInt( container.css( 'padding-top' ),10 ) 
			  - parseInt( container.css( 'padding-bottom' ), 10 ) ) - 100;
            if( img.width > maxWidth || img.height > maxHeight ) { // One of these is larger than the window
                var ratio = img.width / img.height;
                if( img.height >= maxHeight ) {
                    img.height = maxHeight;
                    img.width = maxHeight * ratio;
                } else {
                    img.width = maxWidth;
                    img.height = maxWidth * ratio;
                }
            }
               
				container.animate( calculateImageContainerSize(img),'normal', function() {
				target.append( img );
                jQuery( img ).fadeIn( 'normal', function() {
                    container.removeClass( 'loading' );
		
                } );
            } );
        }
        img.src = url;
		
		currentImage = img;
 
 }// loadImage(url)
 
 
 function calculateImageContainerSize(img)
 {
	if (notThere(img)) { return; }
	widthFix = 0;
	//if ( $.browser.msie ) {
	//  widthFix = 25;
	//}
	//jQuery(img).css({"border": "4px double black"});
	//console.log('scroll '+jQuery(window).scrollTop());
	return {'width': img.width + widthFix, 
	        'height': img.height, 
	'top':  Math.round( ($( window ).height() - img.height - parseInt( container.css( 'padding-top' ),10 ) - parseInt( container.css( 'padding-bottom' ),10 ) ) / 2 ) +  jQuery(window).scrollTop()+ 'px', 
	'left': Math.round( ($( window ).width() - img.width - parseInt( container.css( 'padding-left' ),10 ) - parseInt( container.css( 'padding-right' ),10 ) ) / 2 ) + 'px'
	
	};
	
 
 }

 /*
  resize the display window based on the current image
 
 */
 function windowResize()
 {
   if (notThere(currentImage)) { return; }
   overlay.css({'top': jQuery(window).scrollTop()+'px'});
   container.css(calculateImageContainerSize(currentImage));
 }
 
 
 /*
   close the overlay window
 
 */
 function doCloseWindow(c)
 {

	c.preventDefault();
	overlay.add( container ).fadeOut( 'normal', function()
    {
		container.css(resetContainer());
		currentImage = null;
    });
	
	
    

 } 
	
/* end private    */	
	return {
	
		'initialize' : function()
		{
			//console.log("support opacity "+jQuery.support.opacity);
			
			//if (jQuery.support.opacity == false)
			//{
			//	overlay.toggleClass('overlayClass overlayClassIE');
			
			//}
			
			jQuery('body').append(overlay).append( container );
			container.append( close ).append(target).css(resetContainer());
				close.click( 
				function( c ) {
				  doCloseWindow(c);
				});
				
				jQuery(window).resize(function() {
				   windowResize();
				
				});
				
				jQuery(window).scroll(function() {
					
					windowResize();
				});
				
				
			
		}, // end initialize
		
		'assignLinks' : function() {
		    links =jQuery( 'a[rel^=pixDisplay]' );
			links.each( function( index ) {
				var link = $( this );
				var imgLink = link.find('> img');
				imgLink.attr('border','0');
				link.click( function( c ) {
				c.preventDefault();
				displayImage( link.attr( 'href' ) );
				
        } ); // end assignLinks
        
    } ); // end public functions
		}
		
	};

}();