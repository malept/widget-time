nginx:
  daemon: 'on'
  gzip_comp_level: 5
  gzip_min_length: 256
  server_tokens: 'off'
  tcp_nopush: 'on'
  use_upstart: false
  user_auth_enabled: false
  status_endpoint: false
