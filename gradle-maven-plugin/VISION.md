## Gradle Maven Plugin Vision

### Goals
- Support the latest version of gradle
- Be compliant with standard Maven specs
- make it easy to add this plugin to new projects
- make it easy to maintain a project with this plugin

### Scope
#### Things we will not support:
- Backward compatibility. Use dcendents maven plugin for older pre-5.0 Gradle versions

You can, of course, implement anything you want on top of this library on your own and maintain it, but we are not going to include it if it's something specific to a single use-case. We prefer snippets and posts on StackOverflow for that specific scenarios.

### Technical Considerations
- **Backports**:
We will not make new features available on older versions of this library. Only the latest will have all the features.
