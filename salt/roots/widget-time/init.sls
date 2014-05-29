/etc/nginx/sites-available/widget-time.conf:
  file.managed:
    - source: salt://widget-time/files/nginx.conf.jinja
    - mode: 644
    - template: jinja
    - watch_in:
      service: nginx

/etc/nginx/sites-enabled/widget-time.conf:
  file.symlink:
    - target: ../sites-available/widget-time.conf
    - watch_in:
      service: nginx
