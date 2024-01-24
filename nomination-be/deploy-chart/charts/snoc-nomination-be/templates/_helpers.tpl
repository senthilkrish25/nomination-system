{{- define "snoc.labels"}}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end}}

 {{- define "snoc.envvars"}}
- name: TZ
  value: UTC
- name: NAMESPACE_NAME
  value: {{ .Release.Namespace }}
{{- end}}
