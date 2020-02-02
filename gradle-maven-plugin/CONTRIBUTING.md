## Contributing to Gradle Maven Plugin

Thank you for your interest in Gradle Maven Plugin! There are multiple ways you can contribute to this project. We welcome contributions in all areas, with special attention to:

* [Issue fixes](#issue-fixes)
* [Improvements](#improvements)
* [Documentation](#documentation)

Please take the time to carefully read the following guide. These rules help make the best out of your time, the code reviewer's time and the general consistency of the project.

### General rules

All contributions are handled via Pull Requests (PRs). Your PR _must_ target the `develop` branch. This is the place where we aggregate all upcoming changes for the next release of Gradle Maven Plugin. Moreover, your PR _must_ provide a meaningful description of what it is about.

We suggest using ([Gitflow](http://nvie.com/posts/a-successful-git-branching-model/) branching model).

Don't forget to update `CHANGELOG.md` before pushing your PR. While text may be re-worded before release, but it'll help tracking the changes.

Quick checklist summary before submitting a PR:

* 🔎 Make sure tests are added or updated to accomodate your changes. We do not accept any addition that come without tests. When possible, add tests to verify bug fixes and prevent future regressions.
* 📖 Check that you provided a CHANGELOG entry documenting your changes (except for documentation improvements)
* 👌 Verify that tests pass
* 👍 Push it!

### Issue fixes

Fixing issues is a good way to start contributing and getting used to the large codebase in project! You may want to look at outstanding reported issues, or maybe you bumped into an issue that you found. In the latter case, please make sure you first open an issue in the issue tracker here on GitHub and indicate that you're working on a fix. This will give a chance to other contributors to chime in, and help tracking who's working on what in order to avoid duplicate work.

Once you believe the issue is fixed, make sure the tests pass (see above) then open a Pull Request.

Congratulations on contributing a fix! We love receiving new bug fixes and your help is very much welcomed.

### Improvements

If you think you can Gradle Maven Plugin, create a new issue and explain with as much as details as possible. Discussing the matter via an issue is a preferred starting point. This will allow other contributors to join and express their point of view, allowing for a smooth glide from problem description to resolution.

We take performance very much to heart. Gradle Maven Plugin is at the core of some large products. Performance improvements are always welcome! If you identified a bottleneck, please make sure you follow the performance fix procedure:

* Prepare a reproducible case that highlights the performance issue, if possible. At least, the case should provide a testable timing result.
* Submit a PR with a fix that provides a measurable performance improvement
* Think hard about all the use cases! Threading and concurrency are important to think about when it comes to performance, make sure your fix doesn't come with a performance regression in some use cases.

As previously highlighted, discussing the matter via an issue is a preferred starting point. This will allow other contributors to join and express their point of view, allowing for a smooth glide from problem description to resolution.

Thanks for caring about performance! Gradle Maven Plugin is a crucial component of many applications and performance issues can have a wide impact.

### Documentation

If you spotted a place where documentation could be improved (be in it-line documentation of project markdown pages), please feel free to submit a documentation improvement PR. We very much need a documentation that is as good, as as up-to-date as possible!

We understand the need for foreign language documentation. Unfortunately, due to the scope and breadth of the project it's a tough promise to keep up-to-date documentation in other languages than English. Moreover, all contributors only have English as a common language on the project. Therefore, and to keep the project as maintainable as possible, we only accept documentation changes and improvements provided in English. If you're looking at providing a translation of the in-line documentation, please make sure you have the resources and time to keep it updated as the framework changes. We care a lot about the quality of both the code and its documentation, over the long term. Maintaining a foreign language translation is a longtime commitment that should not be taken lightly.

Thank you for your interest in helping with documentation! Your contributions will make the life of other developers easier.


## Developer's Certificate of Origin 1.1

By making a contribution to this project, I certify that:

- (a) The contribution was created in whole or in part by me and I
      have the right to submit it under the open source license
      indicated in the file; or

- (b) The contribution is based upon previous work that, to the best
      of my knowledge, is covered under an appropriate open source
      license and I have the right under that license to submit that
      work with modifications, whether created in whole or in part
      by me, under the same open source license (unless I am
      permitted to submit under a different license), as indicated
      in the file; or

- (c) The contribution was provided directly to me by some other
      person who certified (a), (b) or (c) and I have not modified
      it.

- (d) I understand and agree that this project and the contribution
      are public and that a record of the contribution (including all
      personal information I submit with it, including my sign-off) is
      maintained indefinitely and may be redistributed consistent with
      this project or the open source license(s) involved.

*Wording of statement copied from [elinux.org](http://elinux.org/Developer_Certificate_Of_Origin)*
