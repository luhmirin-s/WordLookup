# T9 Word Lookup

Android application that searches for matching words (in predefined dictionary) using the numeric keypad.

[Requirements description](https://gist.github.com/axelarge/df8f3f844d1f4ef60caf)

### Notable features:
* MVP architecture (at least how I see it)
* Unit tests
* Submodule fully in Kotlin

### Some rationale behind design decisions:

#### In-memory storage? Seriously?
Requirements prohibited use of any `ready-made database solutions`, so guided by KISS principle I went for naive implementation first and surprisingly it was fast enough to keep it (at least on existing data set). If dictionary size is multiplied several times, than it would be wise to consider other options.

#### Where is Dagger?
Main reason is that application is too trivial for such tools. Second reason is more personal - I have not used in a while and bootstrapping would take too much time. Anyway, in this case manual DI does the same job efficiently enough.

#### Where is RxJava?
See above. :) Although if performance was not that good with naive implementation, I would have definitely switched to Rx and more complicated threading model.

#### Why Kotlin in submodule?
Collections API mostly. Also to show that I actually know a thing or two about it.

#### Why no Kotlin in app?
To show that I also know some Java, also I wanted to try out Jack in some actually useful project :)

#### Why UI tests are so poor?
Same as Dagger, I have not done UI testing in a while (due to current job specifics) and setting everything up to work with IdlingResources is a bit of a pain. Also I am still not sure if I am doing it correctly. Hopefully the fact that there are tests at all proves that I am aware those things exist. :)


### Possible future improvements
* Coordinator layout with fancy animations of toolbar search
* Translate everything in Kotlin
* Some Rx for async operation handling
* More UI tests