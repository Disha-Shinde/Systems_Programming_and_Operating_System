%%
"//".*																                          {printf("%s-SingleLineComment", yytext);}
"/*"[^*^/]*"*/"														                      {printf("%s-MultiLineComment", yytext);}
"class"|"int"|"String"|"public"|"static"|"void"|"main"|"new"		{printf("%s-Keyword", yytext);}
[\{\}\(\)\[\]]														                      {printf("%s-Parenthesis", yytext);}
\".*\"																                          {printf("%s-StringConstant", yytext);}
[0-9]*\.[0-9]*													                        {printf("%s-FloatingConstant", yytext);}
[a-zA-Z]+															                          {printf("%s-Identifier", yytext);}
[\+\-\*\/\%\=]												                         	{printf("%s-Operators", yytext);}
[0-9]+																                          {printf("%s-IntegerConstant", yytext);}	
[;,\.]																                          {printf(" %s-Delimiter", yytext);}	
%%

void main()
{
	yyin = fopen("Input.txt", "r");
	yylex();
}

int yywrap()
{
	return 1;
}
