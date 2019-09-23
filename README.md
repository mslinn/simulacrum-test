# SimulacrumTest

[![License](https://licensebuttons.net/p/zero/1.0/88x31.png)](https://creativecommons.org/share-your-work/public-domain/cc0/)
[![GitHub version](https://badge.fury.io/gh/mslinn%2Fsimulacrum-test.svg)](https://badge.fury.io/gh/mslinn%2Fsimulacrum-test)

## Running the Program
The `bin/run` Bash script assembles this project into a fat jar and runs it.
Sample usage, which runs the `Hello` entry point in `src/main/scala/Hello.scala`:

```bash
$ bin/run
$ bin/run Hello
```

The other entry points are `Hello2` and `NonMonoid`.

```bash
$ bin/run Hello2
$ bin/run NonMonoid
```

The `-j` option forces a rebuild of the fat jar. 
Use it after modifying the source code.

```bash
$ bin/run -j
$ bin/run -j NonMonoid
```
