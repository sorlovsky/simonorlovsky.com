#!/bin/bash
lein clean
lein cljsbuild once min
aws s3 sync resources/public/ s3://simonorlovsky.com --acl public-read
aws cloudfront create-invalidation --distribution-id EECOTLV5NCY7 --paths /\*
