import resolve from 'rollup-plugin-node-resolve'
import json from 'rollup-plugin-json'
import commonjs from 'rollup-plugin-commonjs'
import replace from 'rollup-plugin-replace'
import buble from 'rollup-plugin-buble'
import riot from 'rollup-plugin-riot'
import postcss from 'postcss'
import postcssCssnext from 'postcss-cssnext'

export default {
  entry: 'riot/index.js',
  dest: 'public/javascripts/bundle.js',
  plugins: [
    riot({
      style: 'cssnext',
      parsers: {
        css: { cssnext },
      },
    }),
    resolve({
      jsnext: true,
      browser: true,
    }),
    replace({'process.env.NODE_ENV': JSON.stringify('production')}),
    commonjs(),
    json(),
    buble(),
  ],
  format: 'iife',
}

/**
 * Transforms new CSS specs into more compatible CSS
 */
function cssnext (tagName, css) {
  css = css.replace(/:scope/g, ':root')
  css = postcss([postcssCssnext]).process(css).css
  css = css.replace(/:root/g, ':scope')
  return css
}
