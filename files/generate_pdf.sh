#!/bin/sh

dot2tex -ftikz --autosize $1.dot > $1.tex 

pdflatex -interaction nonstopmode $1.tex
echo "$1.pdf generated"
rm -f *.aux *.log *.toc *.out *.lot *.lof secciones/*.aux secciones/*.log
