[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.robinpcrd/cupertino-core)](https://central.sonatype.com/artifact/io.github.robinpcrd/cupertino-core)

[![Main Workflow](https://github.com/robinpcrd/compose-cupertino/actions/workflows/buildAndPush.yml/badge.svg)](https://github.com/robinpcrd/compose-cupertino/actions/workflows/buildAndPush.yml)

# compose-cupertino

This is a fork of [schott12521/compose-cupertino](https://github.com/schott12521/compose-cupertino)

Additionally this repo has automated builds to enable faster releases, to take advantage of new compose multiplatform features as they become available.

## New features (compared to the OG `compose-cupertino`)

- Updated SwipeBox
- Fixed Cupertino Date Picker implementations to interact with scrolling correctly
- Disable content scaling on CupertinoBottomSheetScaffold (https://github.com/alexzhirkevich/compose-cupertino/issues/80)
- Using Dialogs instead of Popups for CupertinoDialogs (https://github.com/alexzhirkevich/compose-cupertino/issues/78)

# Usage

This package is published to Maven Central Repository: [cupertino-core on Maven Central](https://central.sonatype.com/artifact/io.github.robinpcrd/cupertino-core)

Depend on the [latest version](https://github.com/robinpcrd/compose-cupertino/releases) by declaring this in libs.versions.toml:

```
cupertino = "$latestVersion"

cupertino = { group = "io.github.robinpcrd", name = "cupertino", version.ref = "cupertino" }
cupertino-adaptive = { group = "io.github.robinpcrd", name = "cupertino-adaptive", version.ref = "cupertino" }
cupertino-decompose = { group = "io.github.robinpcrd", name = "cupertino-decompose", version.ref = "cupertino" }
cupertino-native = { group = "io.github.robinpcrd", name = "cupertino-native", version.ref = "cupertino" }
cupertino-icons-extended = { group = "io.github.robinpcrd", name = "cupertino-icons-extended", version.ref = "cupertino" }
```

## 🔄 Migration

### From alexzhirkevich/compose-cupertino

If you're migrating
from [alexzhirkevich/compose-cupertino](https://github.com/alexzhirkevich/compose-cupertino), update
your imports:

```kotlin
// Before
import io.github.alexzhirkevich.cupertino.*

// After  
import io.github.robinpcrd.cupertino.*
```

**Find and replace across your project:**

- Find: `io.github.alexzhirkevich.cupertino`
- Replace: `io.github.robinpcrd.cupertino`

### From schott12521/compose-cupertino

If you're migrating
from [schott12521/compose-cupertino](https://github.com/schott12521/compose-cupertino), update your
imports:

```kotlin
// Before
import com.slapps.cupertino.*

// After
import io.github.robinpcrd.cupertino.*
```

**Find and replace across your project:**

- Find: `com.slapps.cupertino`
- Replace: `io.github.robinpcrd.cupertino`

## Try it

Wanna see what the library feels like? The latest version builds and deploys the Kotlin/WasmJS target to github pages: https://robinpcrd.github.io/compose-cupertino/

# License

Note: As of release 3.0.0, this forked library has been updated copyright headers to fully comply
with the Apache 2.0 license copied from the
original: https://github.com/alexzhirkevich/compose-cupertino/blob/master/LICENSE.txt

# Contributions

Contributions are always appreciated! Since builds to maven central go through GitHub Actions, we
can get your changes in quickly.

Ensure that the copyright information is included in your file(s):

```
/*
 * Copyright (c) 2023-2024. Compose Cupertino project and open source contributors.
 * Copyright (c) 2025. Scott Lanoue.
 * Copyright (c) 2025. Robin Picard.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
```

Ideally you set up Android Studio / IDE to handle copyright notices for you: https://stackoverflow.com/a/48718711/1730421

# Disclaimer

Forked to maintain active development as upstream projects have been inactive for months.
