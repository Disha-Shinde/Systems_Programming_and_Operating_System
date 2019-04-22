%{
	int word_count, char_count, space_count, no_count;
%}

%%
[a-zA-Z]+			{word_count++; char_count += yyleng;}
[ \t\n]*			{space_count++;}
[0-9]+				{no_count++; char_count += yyleng;}
%%

void main()
{
	yyin = fopen("input.txt", "r");
	yylex();
	printf("Number of words : %d\n", word_count);
	printf("Number of characters : %d\n", char_count);
	printf("Number of spaces : %d\n", space_count);
	printf("Count of numbers : %d\n", no_count);
}

int yywrap()
{
	return 1;
}
