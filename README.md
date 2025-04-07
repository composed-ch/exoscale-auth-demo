# exoscale-auth-demo

Demo application for
[exoscale-auth](https://github.com/composed-ch/exoscale-auth) library.

## Usage

Define your Exoscale API credentials and zone in a file called `conf.edn` in
the project's root directory:

```clojure
{:exoscale-api-key "EXO…"
 :exoscale-api-secret "…"
 :exoscale-zone "ch-gva-2"}
```

Adjust the requests to be made in `src/exoscale_auth_demo/core.clj`, install
the dependencies, and run it:

```
lein install && lein run
```
