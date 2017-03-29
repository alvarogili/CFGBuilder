package ar.edu.unrc.asp.cfgbuilder.parser;

import java_cup.runtime.Symbol;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

%%
%class LexicalParser
%public
%line
%column
%cup

%eofval{
    return new Symbol(sym.EOF);
%eofval}

%{   

    public Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }

    public Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

Identifier = [:jletter:] [:jletterdigit:]*
Integer = 0 | [1-9][0-9]*

%%
<YYINITIAL> {
    {WhiteSpace}         {/*                ignore                   */}
    "+"                  { return symbol(sym.PLUS);                    }
    "-"                  { return symbol(sym.MINUS);                   }
    "*"                  { return symbol(sym.TIMES);                   }
    "/"                  { return symbol(sym.SLASH);                   }
    "("                  { return symbol(sym.LPAREN);                  }
    ")"                  { return symbol(sym.RPAREN);                  }
    "{"                  { return symbol(sym.LBRACE);                  }
    "}"                  { return symbol(sym.RBRACE);                  }
    ":="                 { return symbol(sym.BECOMES);                 }
    "=="                 { return symbol(sym.EQL);                     }
    "<"                  { return symbol(sym.LSS);                     }
    ">"                  { return symbol(sym.GRT);                     }
    "do"                 { return symbol(sym.DOSYM);                   }
    "if"                 { return symbol(sym.IFSYM);                   }
    "else"               { return symbol(sym.ELSESYM);                 }
    "then"               { return symbol(sym.THENSYM);                 }
    "while"              { return symbol(sym.WHILESYM);                }
    {Integer}            { return symbol(sym.INT,(new Integer(yytext())).toString());}
    {Identifier}         { return symbol(sym.ID,new String(yytext())); }
    [^]                  {System.out.println("Invalid token <" + yytext()+ 
                            "> line: " + (yyline+1) + "column " + (yycolumn+1));}
    
}