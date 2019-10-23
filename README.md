# String Parameters Library

## What?
String interpolation made neat. A (really) small library with useful functionality to infer your parameters to strings

## Why?
Ever got annoyed by that String.format() syntax? With '%s' and '%d' placeholders all over the place and additional need to keep track of parameter count and ordering on top of that? And also names... no names, actually.

This library allows you to put named and indexed parameters into your strings as well as mix and match them however you want.

## But wait... That's kind of like templates, right?
Right. If you want to import a full-fledged template engine to your project just to make that single error message or log line coded pretty - go for it. There are a few really good ones around.

## And if I don't?
Then just use the Force, Luke:
```
Parametrized.of("Hello, #{name}! It's a wonderful {}!")
    .with("name", "World")
    .with(1, "day")
    .build();

// Result

"Hello, World! It's a wonderful day!"
```

Here. Simple as that. No fancy tech, no super-powerful reflection tricks. 

Just String Parameters. Done neat.