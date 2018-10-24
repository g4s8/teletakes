# Teletakes
True Object-Oriented Telegram Framework with same design as in https://www.takes.org/


[![Codacy Badge](https://api.codacy.com/project/badge/Grade/19163ae3ff1c4bfca6a3cb387b03279d)](https://app.codacy.com/app/g4s8/teletakes?utm_source=github.com&utm_medium=referral&utm_content=g4s8/teletakes&utm_campaign=Badge_Grade_Dashboard)
[![EO principles respected here](http://www.elegantobjects.org/badge.svg)](http://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/zerocracy/farm)](http://www.rultor.com/p/zerocracy/farm)

[![CircleCI](https://circleci.com/gh/g4s8/teletakes.svg?style=svg)](https://circleci.com/gh/g4s8/teletakes)
[![Build Status](https://travis-ci.org/g4s8/teletakes.svg?branch=master)](https://travis-ci.org/g4s8/teletakes)
[![Build status](https://ci.appveyor.com/api/projects/status/lxpj90a6h5q8mgef?svg=true)](https://ci.appveyor.com/project/g4s8/teletakes)

[![codecov](https://codecov.io/gh/g4s8/teletakes/branch/master/graph/badge.svg)](https://codecov.io/gh/g4s8/teletakes)
 [![Download](https://api.bintray.com/packages/g4s8/mvn/com.g4s8.teletakes/images/download.svg) ](https://bintray.com/g4s8/mvn/com.g4s8.teletakes/_latestVersion)
[![PDD status](http://www.0pdd.com/svg?name=g4s8/teletakes)](http://www.0pdd.com/p?name=g4s8/teletakes)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/g4s8/teletakes/blob/master/LICENSE.txt)

## Examples

### Quick start
This example will answer for "hello" message with "Hello %username%" message:
```java
 new TelegramBotsApi().registerBot(
    new BotSimple(
      new TkFork(
        new FkMessage(
            "hello",
            req -> new RsText(
                new FormattedText(
                    "Hello %s", new RqUser(req).value().getFirstName()
                )
            )
        )
      )
    )
 );
 ```
 
 ### Handle commands
 This take answers for `/version` command:
 ```java
 new TkFork(
    new FkCommand(
      "/version"
      new TkText("1.0")
    )
)
```

### Show keyboard
This take shows keyboard on user Telegram client:
```java
new TkFixed(
    new RsReplyKeyboard(
        new RsText("Hello"),
        new ListOf<Iterable<Text>>(
            new ListOf<>(
                new TextOf("Menu"),
                new TextOf("Settings")
            )
        )
    )
)
```

### Inline buttons
Telegram message can include "inline buttons" with callbacks, where callback is a strings which will be sent
sent to a bot on button click. This take will show a button and handle its clicks:
```java
new TkFork(
    new FkMessage(
        Pattern.compile(".*"),
        new TkFixed(
            new RsInlineKeyboard(
                new RsText("inline buttons"),
                new ListOf<Iterable<Map.Entry<String, String>>>(
                    new ListOf<Map.Entry<String, String>>(
                        new MapEntry<>("Confirm", "click:on-confirm"),
                        new MapEntry<>("Cancel", "click:on-cancel")
                    )
                )
            )
        )
    ),
    new FkCallbackQuery(
        "click:on-confirm",
        new TkText("Confirmed")
    ),
    new FkCallbackQuery(
        "click:on-cancel",
        new TkText("Canceled")
    )
)
```
