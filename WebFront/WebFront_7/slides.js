$('.sl').slick({
    dots:true,
    infinite: true,
    slidesToShow: 4,
    slidesToScroll: 4,
    responsive: [
        {
          breakpoint: 768,
          settings: {
            arrows: false,
            slidesToShow: 2,
            slidesToScroll: 2
          }
        },
        {
          breakpoint: 480,
          settings: {
            arrows: false,
            slidesToScroll: 2,
            slidesToShow: 2
          }
        }
      ]
});