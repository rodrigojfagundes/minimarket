import { Component, AfterViewInit } from '@angular/core';
import jQuery from 'jquery';

@Component({
  selector: 'app-root',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css'],
})
export class LayoutComponent implements AfterViewInit {
  title = 'minimarket-app';

  ngAfterViewInit(): void {
    (function ($) {
      'use strict';

      var path = window.location.href;
      $('#layoutSidenav_nav .sb-sidenav a.nav-link').each(function () {
        if (this.href === path) {
          $(this).addClass('active');
        }
      });

      $('#sidebarToggle').on('click', function (e) {
        e.preventDefault();
        $('body').toggleClass('sb-sidenav-toggled');
      });
    })(jQuery);
  }
}
