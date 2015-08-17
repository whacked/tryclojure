# tryclojure with overtone HACK

TryClojure is a online Clojure REPL written using Noir and Chris Done's jquery console (you're awesome, Chris).

[<img src="https://secure.travis-ci.org/Raynes/tryclojure.png"/>](http://travis-ci.org/Raynes/tryclojure)

## NOT SAFE FOR PUBLIC USE!!!

this is modified to load the [overtone](https://github.com/overtone/overtone)
library so that you can interact with overtone through the tryclj. It is
intended to be used in demonstration settings.

see `views/eval.clj` for the hack. All we do, is *bypass the sandbox*, and
**eval-ing expressions as-is**! You probably do not want to be using this!

### Other caveats

- forcing all incoming expressions to evaluate in the `tryclojure.views.eval` ns
- large requests will cause `WARN:oejh.HttpParser:HttpParser Full` issue.
  the expression is not eval-ed, and the page needs to be reloaded.
  workaround is simply to use a smaller expression.

## Usage

http://tryclj.com

To run it locally, use `lein ring server`.

## Credits

apgwoz: Design

## License

Licensed under the same thing Clojure is licensed under: the EPL, of which you can find a copy at the root of this directory.
