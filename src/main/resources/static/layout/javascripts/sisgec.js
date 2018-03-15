var MARAJO = MARAJO || {};

MARAJO.onSidebarToggleRequest = function(event) {
  event.preventDefault();
  $(this).blur();

  $('.js-sidebar, .js-content').toggleClass('is-toggled');
};

MARAJO.onSearchModalShowRequest = function(event) {
  event.preventDefault();

  $('.js-search-modal').fadeIn('slow');
  $('body').addClass('marajo-no-scroll');
  
  $('.js-search-modal-input').val('').select();
  
};

MARAJO.onSearchModalCloseRequest = function(event) {
  event.preventDefault();

  $('.js-search-modal').hide();
  $('body').removeClass('marajo-no-scroll');
};

//MARAJO.onFormLoadingSubmit = function(event) {
  //event.preventDefault();

  //MARAJO.showLoadingComponent();

  //setTimeout(function() {
  //  MARAJO.hideLoadingComponent();
  //}, 2000);
//};

MARAJO.showLoadingComponent = function() {
  $('.js-loading-overlay').css('display', 'table').hide().fadeIn('slow');
};

MARAJO.hideLoadingComponent = function() {
  $('.js-loading-component').fadeOut('fast');
};

MARAJO.initStickyTableHeaders = function() {
  if ($(window).width() >= 992) { 
    var stickyRef = $('.js-sticky-reference');
    var stickyTable = $('.js-sticky-table');

    if (stickyRef && stickyTable) {
      stickyTable.stickyTableHeaders({fixedOffset: stickyRef});
    }
  }
};

MARAJO.onMenuGroupClick = function(event) {
  var subItems = $(this).parent().find('ul');

  if (subItems.length) {
    event.preventDefault();
    $(this).parent().toggleClass('is-expanded');
  }
};

MARAJO.initMenu = function() {
  $('.js-menu > ul > li > a').bind('click', MARAJO.onMenuGroupClick);
  $('.marajo-menu__item .is-active').parents('.marajo-menu__item').addClass('is-expanded is-active');
};

$(function() {
  if (MARAJO.init) {
    MARAJO.init();
  }

  MARAJO.initMenu();
  MARAJO.initStickyTableHeaders();
  
  // Enable Bootstrap tooltip
  $('.js-tooltip').tooltip();
  
  // Bind events
  $('.js-sidebar-toggle').bind('click', MARAJO.onSidebarToggleRequest);
  $('.js-search-modal-trigger-show').bind('click', MARAJO.onSearchModalShowRequest);
  $('.js-search-modal-close').bind('click', MARAJO.onSearchModalCloseRequest);
  //$('.js-form-loading').bind('submit', MARAJO.onFormLoadingSubmit);
});