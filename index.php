<?php

	$db_host = "localhost";
	$db_name = "lashle63";
	$db_user = "lashle63		";
	$db_pass = "1PurpLe0mouse!";
	
	$conn = mysqli_connect($db_host, $db_user, $db_pass, $db_name);
	
	if (mysqli_connect_error()){
		echo mysqli_connect_error();
		exit;
	}
	
	
	
	
	$sql = "SELECT *
			FROM article
			order by published_at;";
			
			
	$results = mysqli_query($conn, $sql);
	
	
	if ($results === false){
		echo mysqli_error($conn);
	}else{
		$articles = mysqli_fetch_all($results);
	
	}
	
	?>
	
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title> Intro To web Dev | Home Page </title>
</head>
<body>
	
		<header>
			<h1> My Blogs </h1>
		</header>


			<main>
				<?php if (empty($articles)): ?>
					<p>No articles found. =( </p>
				<?php else: ?>
			
				<ul>
					<?php foreach ($articles as $article): ?>
					<li>
						<article>
							<h2><a href="Article.php?id=<?= #article['id']"> <?= $article['title']; ?> </a> </h2>
							<p><?= $article['content']; ?></p>
						</article>
					</li>
					<?php endforeach; ?>
				</ul>
				<?php endif; ?>
				
			</main>
			
			
</body>
</html>