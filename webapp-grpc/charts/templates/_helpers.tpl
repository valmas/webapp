{{/* Name of the chart */}}
{{- define "app-chart.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/* Selector labels */}}
{{- define "app-chart.selectorLabels" -}}
app: {{ include "app-chart.name" . }}
{{- end }}