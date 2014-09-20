###
# nicker de ligne
###

print ("Hello ! Bienvenue dans le nicker de ligne vide !\n");
print ("================================================\n\n");
print ("Entrer le nom du fichier à formater : \n\n");

$file = <STDIN>;
print ("le fichier est : " . $file . "\n");

if ($file ne "") {
	open (FILE, "$file") || print("putain, pas moyen d'ouvrir ton fichier !\n");
	@myFile = <FILE>;
	close FILE;

	open (FILEW, ">$file") || print("putain, pas moyen d'ouvrir ton fichier en écriture\n");
	$nbreLine = 0;
	foreach $line (@myFile) {
		if ($line ne "\n") {
			print FILEW $line;
		}
		$nbreLine++;
	}
	close FILEW;
	print ("Opération terminée : $nbreLine ligne(s) !!!\n\n");
}
else {
 print ("Le fichier $file n'existe pas !\n\n");
}

print ("Appuyer sur Enter pour killer le script !!!");
$pause=<STDIN>;
exit(0);